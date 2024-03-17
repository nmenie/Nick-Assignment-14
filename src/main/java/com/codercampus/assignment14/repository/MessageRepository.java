package com.codercampus.assignment14.repository;

import java.util.List;

import com.codercampus.assignment14.domain.Message;

public interface MessageRepository {
    Message save(Message message);
    List<Message> findAll();
    Message findById(Long id);
	void setSender(Long messageId, String sender);
	List<Message> findByChannelIdAndIdGreaterThanAndSenderUsernameNot(Long channelId, Long lastMessageId,
			String username);
}


