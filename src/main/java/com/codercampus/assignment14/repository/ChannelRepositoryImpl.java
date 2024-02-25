package com.codercampus.assignment14.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.Channel;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {
    private final Map<String, Channel> channels = new HashMap<>();

    @Override
    public Channel createChannel(String id, String name) {
        Channel channel = new Channel(id, name);
        channels.put(id, channel);
        return channel;
    }

    @Override
    public Channel getChannel(String id) {
        return channels.get(id);
    }

    @Override
    public List<Channel> getChannels() {
        return new ArrayList<>(channels.values());
    }

}
