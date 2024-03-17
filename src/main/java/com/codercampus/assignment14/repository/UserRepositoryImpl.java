package com.codercampus.assignment14.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(nextId.getAndIncrement());
        }
        users.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User findByUserId(Long userId) {
        return users.get(userId);
    }

    @Override
    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
