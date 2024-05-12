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
        return "redirect:/channel";
    }
    
    
    
    
    @GetMapping("/channels")
    public String getChannels( ModelMap model, @RequestParam("username") String username) {
    	
    	Channel newChannel = new Channel();
    	newChannel.setName("General Channel");
    	model.put("username", username);
    	model.put("channels", List.of(newChannel));
    	return "channels";
    }
    
   
    
    @GetMapping("/channel")
    public String channel(ModelMap model, @PathVariable Long userId) {
        User user = userService.findById(userId);
        System.out.println(user.getUsername());
        Channel generalChannel = channelService.GeneralChannel();
      
        model.put("user", user);
        model.put("generalChannel", generalChannel);
        
        
        return "channel";
    }
    
    @GetMapping("/channels/{channelId}")
    public String getGeneralChannel(@PathVariable Long channelId, ModelMap model) {
        
        Optional<Channel> channel = channelService.findById(channelId);
        
        model.put("channel", channel);
        
        return "general-channel";
    }
//    
//    @PostMapping("/channels/{channelId}/messages")
//    public Message getMessages(
//            @PathVariable Long channelId,
//            @RequestParam("content") String content,
//            @RequestParam("username") String username) {
//        Channel channel = channelService.GeneralChannel();
//        Message message = new Message();
//        message.setChannel(channel);
//        message.setContent(content);
//        message.setUser(username);
//        message.setTimestamp(new LocalDateTime(null, null));
//        Message savedMessage = messageService.saveMessage(message);
//        return savedMessage;
//    }
//    
    
    @GetMapping("/channels/{channelId/messages}")
    public List<Message> getAllMessages(@PathVariable int channelId) {
    	return channelService.findAll();
    }
        
    
   
}

