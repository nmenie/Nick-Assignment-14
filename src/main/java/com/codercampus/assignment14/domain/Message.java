package com.codercampus.assignment14.domain;

import java.time.LocalDateTime;

public class Message {
    private String id;
    private String userId;
    private String channelId;
    private String content;
    private LocalDateTime timestamp;
	private String text;

    public Message(String id, String userId, String channelId, String text) {
        this.id = id;
        this.userId = userId;
        this.channelId = channelId;
        this.text = text;
    }
    
    

	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    
}