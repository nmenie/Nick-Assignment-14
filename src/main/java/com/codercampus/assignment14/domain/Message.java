package com.codercampus.assignment14.domain;

import java.time.LocalDateTime;

public class Message {
	private Long channelId;
    private Long id;
    private String content;
    private String senderUsername;
    private LocalDateTime timestamp;

    public Message() {
        // Default constructor
    }

    public Message(Long id, String content, String senderUsername, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.senderUsername = senderUsername;
        this.timestamp = timestamp;
    }
    
    

    public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}