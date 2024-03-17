package com.codercampus.assignment14.web;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
import com.codercampus.assignment14.repository.ChannelRepository;
import com.codercampus.assignment14.repository.MessageRepository;
import com.codercampus.assignment14.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ChatController {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private Map<String, String> sessionUsernames = new ConcurrentHashMap<>();

    public ChatController(ChannelRepository channelRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/welcome")
    public String welcomePage(ModelMap model, @RequestParam(required = false) String userId) {
        if (userId != null) {
            String username = sessionUsernames.get(userId);
            if (username != null) {
                model.addAttribute("username", username);
            }
        }
        return "welcome";
    }

    @PostMapping("/welcome")
    public String handleWelcome(@RequestParam String username, HttpSession session) {
        String userId = session.getId();
        sessionUsernames.put(userId, username);
        session.setAttribute("userId", userId);
        session.setAttribute("username", username);
        return "redirect:/channels";
    }

    @GetMapping("/channels")
    public String channelsPage(ModelMap model, HttpSession session) {
        List<Channel> channels = channelRepository.findChannels();
        model.addAttribute("channels", channels);
        
        String userId = (String) session.getAttribute("userId");
        String username = sessionUsernames.get(userId);
        model.addAttribute("username", username);
        
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
            model.addAttribute("channel", channel);
            
            String userId = (String) session.getAttribute("userId");
            String username = sessionUsernames.get(userId);
            model.addAttribute("username", username);
            
            return "channel";
        } else {
            return "redirect:/channels";
        }
    }

    @GetMapping("/channels/{channelId}/messages")
    public List<Message> getNewMessages(@PathVariable Long channelId, @RequestParam(defaultValue = "0") Long lastMessageId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        String username = sessionUsernames.get(userId);
        Channel channel = channelRepository.findById(channelId);
        if (channel != null && username != null) {
            return channel.getMessages().stream()
                    .filter(message -> message.getId() > lastMessageId)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @PostMapping("/channels/{channelId}/messages")
    public String sendMessage(@PathVariable Long channelId, @RequestParam String content, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        String username = sessionUsernames.get(userId);
        Channel channel = channelRepository.findById(channelId);
        if (channel != null && username != null) {
            Message message = new Message();
            message.setContent(content);
            message.setSenderUsername(username);
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