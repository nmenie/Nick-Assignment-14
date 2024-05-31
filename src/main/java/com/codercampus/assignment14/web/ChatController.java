package com.codercampus.assignment14.web;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codercampus.assignment14.domain.Channel;
import com.codercampus.assignment14.domain.Message;
import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.MessageRepository;
import com.codercampus.assignment14.service.ChannelService;
import com.codercampus.assignment14.service.MessageService;
import com.codercampus.assignment14.service.UserService;

@Controller
public class ChatController {
   
	@Autowired
	private UserService userService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageRepository messageRepository;
	
	

    @GetMapping("/welcome")
    public String welcomePage(ModelMap model) {
    	
    	 model.put("user", new User());
    	
        return "welcome";
    }

    @PostMapping("/welcome")
    public String createUser(User user) {
    
        userService.saveUser(user);
        return "redirect:/channelS";
    }
    
    
    
    
    @GetMapping("/channels")
    public String getChannels( ModelMap model, @RequestParam("username") String username) {
    	
    	Channel newChannel = new Channel();
    	newChannel.setName("General Channel");
    	model.put("username", username);
    	model.put("channels", List.of(newChannel));
    	return "channels";
    }
    
   
    
//    @GetMapping("/channels")
//    public String channel(ModelMap model, @PathVariable Long userId) {
//        User user = userService.findById(userId);
//        System.out.println(user.getUsername());
//        Channel generalChannel = channelService.GeneralChannel();
//      
//        model.put("user", user);
//        model.put("generalChannel", generalChannel);
//        
//        
//        return "channels";
//    }
    
    @GetMapping("/channels/{channelId}")
    public String getGeneralChannel(@PathVariable Long channelId, ModelMap model) {
        Optional<Channel> channel = channelService.findById(channelId);
        if (channel.isPresent()) {
            model.put("channel", channel.get());
        } else {
            model.put("error", "Channel not found");
        }
        return "channel"; // Ensure "general-channel.html" exists in templates
    }
    
    @GetMapping("/channels/{channelId}/messages")
    @ResponseBody
    public List<Message> getAllMessages(@PathVariable Long channelId) {
        return messageService.getMessagesByChannel(channelId);
    }
}
        
    
   


