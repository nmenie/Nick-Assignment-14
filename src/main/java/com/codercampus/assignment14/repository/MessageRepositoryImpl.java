package com.codercampus.assignment14.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.Message;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final Map<Long, Message> messages = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);
    
    
    @Override
    public List<Message> findByChannelIdAndIdGreaterThanAndSenderUsernameNot(Long channelId, Long lastMessageId, String username) {
        return messages.values().stream()
                .filter(message -> message.getChannelId().equals(channelId))
                .filter(message -> message.getId() > lastMessageId)
                .filter(message -> !message.getSenderUsername().equals(username))
                .collect(Collectors.toList());
    }


    @Override
    public Message save(Message message) {
        if (message.getId() == null) {
            message.setId(nextId.getAndIncrement());
        }
        messages.put(message.getId(), message);
        return message;
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages.values());
    }

    @Override
    public Message findById(Long id) {
        return messages.get(id);
    }

    @Override
    public void setSender(Long messageId, String sender) {
        Message message = findById(messageId);
        if (message != null) {
            message.setSenderUsername(sender);
        }
    }
}

  