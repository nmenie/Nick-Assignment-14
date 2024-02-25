package com.codercampus.assignment14.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.ChannelRepository;
import com.codercampus.assignment14.repository.MessageRepository;
import com.codercampus.assignment14.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {
    private UserService userService;
    private ChannelRepository channelRepository;
    private MessageRepository messageRepository;

    public ChatController(UserService userService, ChannelRepository channelRepository, MessageRepository messageRepository) {
        this.userService = userService;
        this.channelRepository = channelRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String welcome(Model model, HttpSession session) {
        User user = userService.getUser((String) session.getAttribute("user"));
        if (user == null) {
            user = userService.createUser("");
            session.setAttribute("user", user.getId());
        }
        model.addAttribute("user", user);
        return "welcome";
    }

    @PostMapping("/join")
    public String joinChannel(HttpServletRequest request, Model model, HttpSession session) {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            User user = userService.getUser((String) session.getAttribute("user"));
            user.setName(name);
            userService.saveUser(user);
            return "redirect:/channels";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/channels")
    public String channels(Model model, HttpSession session) {
        User user = userService.getUser((String) session.getAttribute("user"));
        model.addAttribute("user", user);
        model.addAttribute("channels", channelRepository.getChannels());
        return "channels";
    }
}