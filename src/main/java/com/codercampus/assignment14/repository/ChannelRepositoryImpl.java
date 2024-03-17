package com.codercampus.assignment14.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.Channel;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {
	
    private Map<Long, Channel> channels = new HashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public Channel save(Channel channel) {
        // Check if a channel with the same name already exists
        Channel existingChannel = findChannelByName(channel.getChannelName());
        if (existingChannel != null) {
            // If a channel with the same name exists, return the existing channel
            return existingChannel;
        }
        // If no channel with the same name exists, generate a new unique identifier
        channel.setId(nextId.getAndIncrement());
        channels.put(channel.getId(), channel);
        return channel;
    }

    @Override
    public Channel findChannelByName(String channelName) {
        return channels.values().stream()
                .filter(channel -> channel.getChannelName().equals(channelName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Channel findById(Long id) {
        return channels.get(id);
    }

    @Override
    public List<Channel> findChannels() {
        return new ArrayList<>(channels.values());
    }
}
