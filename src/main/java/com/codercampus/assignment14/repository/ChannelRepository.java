package com.codercampus.assignment14.repository;

import com.codercampus.assignment14.domain.Channel;

public interface ChannelRepository {
    Channel createChannel(String id, String name);
    Channel getChannel(String id);
	Object getChannels();
}