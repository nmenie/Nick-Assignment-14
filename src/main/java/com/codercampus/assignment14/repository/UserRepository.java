package com.codercampus.assignment14.repository;

import com.codercampus.assignment14.domain.User;

public interface UserRepository {
    User save(User user);
    User findByUserId(Long userId);
    User findByUsername(String username);
}

