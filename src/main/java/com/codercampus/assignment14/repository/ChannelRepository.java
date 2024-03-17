package com.codercampus.assignment14.repository;

import java.util.List;

import com.codercampus.assignment14.domain.Channel;

public interface ChannelRepository {
	
	Channel save(Channel channel);
    Channel findChannelByName(String channelName);
    Channel findById(Long id);
    List<Channel> findChannels();
	

	
   
}