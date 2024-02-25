package com.codercampus.assignment14.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.Message;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final Map<String, List<Message>> messages = new HashMap<>();

    @Override
    public Message createMessage(String id, String userId, String channelId, String text) {
        Message message = new Message(id, userId, channelId, text);
        messages.computeIfAbsent(channelId, k -> new ArrayList<>()).add(message);
        return message;
    }

    @Override
    public List<Message> getMessages(String channelId) {
        return messages.getOrDefault(channelId, Collections.emptyList());
    }
}
