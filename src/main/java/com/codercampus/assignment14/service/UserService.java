package com.codercampus.assignment14.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        String id = UUID.randomUUID().toString();
        return userRepository.createUser(id, name);
    }

    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}


