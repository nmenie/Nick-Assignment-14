package com.codercampus.assignment14.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codercampus.assignment14.domain.Channel;
import com.codercampus.assignment14.domain.Message;
import com.codercampus.assignment14.repository.ChannelRepository;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    public Optional<Channel> findById(Long channelId) {
        return channelRepository.findById(channelId);
                
    }
    
    
    // Store messages to a specific channel
    
    public Message saveMessageInTheChannel(Message message, int channelId) {
    	
    	
    }
    
    // Return all the messages
    

        public Channel GeneralChannel() {
            return channelRepository.findByName("General")
                    .orElseGet(() -> {
                        Channel generalChannel = new Channel();
                        generalChannel.setName("General");
                        return channelRepository.save(generalChannel);
                    });
        }
    }

