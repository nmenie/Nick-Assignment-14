package com.codercampus.assignment14.domain;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Long id;
    private String channelName;
    private List<Message> messages = new ArrayList<>();

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
	



	


	
	
	
	
