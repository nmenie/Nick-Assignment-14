package com.codercampus.assignment14.repository;


import java.util.List;

import com.codercampus.assignment14.domain.Message;

public interface MessageRepository {
    Message createMessage(String id, String userId, String channelId, String text);
    List<Message> getMessages(String channelId);
}



