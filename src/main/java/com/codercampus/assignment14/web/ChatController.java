package com.codercampus.assignment14.web;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codercampus.assignment14.domain.Channel;
import com.codercampus.assignment14.domain.Message;
import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.ChannelRepository;
import com.codercampus.assignment14.repository.MessageRepository;
import com.codercampus.assignment14.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatController(ChannelRepository channelRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/welcome")
    public String welcomePage(ModelMap model, @RequestParam(required = false) Long userId) {
        if (userId != null) {
            User user = userRepository.findByUserId(userId);
            if (user != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("userId", user.getUserId());
            }
        }
        return "welcome";
    }

    @PostMapping("/welcome")
    public String handleWelcome(@RequestParam String username, HttpSession session) {
        User user = new User(username);
        user = userRepository.save(user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", username);
        return "redirect:/channels";
    }

    @GetMapping("/channels")
    public String channelsPage(ModelMap model, HttpSession session) {
        List<Channel> channels = channelRepository.findChannels();
        model.addAttribute("channels", channels);
        
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        if (userId != null && username != null) {
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
        }
     
        
        return "channels";
    }

    @PostMapping("/channels/create")
    public String createChannel(@RequestParam String channelName) {
        Channel channel = new Channel();
        channel.setChannelName(channelName);
        channelRepository.save(channel);
        return "redirect:/channels";
    }

    @GetMapping("/channels/{channelId}")
    public String channelPage(@PathVariable Long channelId, ModelMap model, HttpSession session) {
        Channel channel = channelRepository.findById(channelId);
        if (channel != null) {
            // Eagerly load the User object for each message and set the senderUsername
            for (Message message : channel.getMessages()) {
                User sender = userRepository.findByUserId(message.getId());
                if (sender != null) {
                    message.setSenderUsername(sender.getUsername());
                }
            }
            model.addAttribute("channel", channel);
            Long userId = (Long) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");
            if (userId != null && username != null) {
                model.addAttribute("userId", userId);
                model.addAttribute("username", username);
            }
            return "channel";
        } else {
            return "redirect:/channels";
        }
    }

    @PostMapping("/channels/{channelId}/messages")
    public String sendMessage(@PathVariable Long channelId, @RequestParam String content, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userRepository.findByUserId(userId);
        Channel channel = channelRepository.findById(channelId);
        if (channel != null && user != null) {
            Message message = new Message();
            message.setContent(content);
            message.setSenderUsername(user.getUsername());
            message.setTimestamp(LocalDateTime.now());
            message = messageRepository.save(message);
            channel.getMessages().add(message);
            channelRepository.save(channel);
        }
        return "redirect:/channels/" + channelId;
    }

    @ModelAttribute("generalChannel")
    public Channel getGeneralChannel() {
        Channel generalChannel = channelRepository.findChannelByName("General");
        if (generalChannel == null) {
            generalChannel = new Channel();
            generalChannel.setChannelName("General");
            channelRepository.save(generalChannel);
        }
        return generalChannel;
    }
}