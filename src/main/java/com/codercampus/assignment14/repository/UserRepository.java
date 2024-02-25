package com.codercampus.assignment14.repository;

import com.codercampus.assignment14.domain.User;

public interface UserRepository {
    User createUser(String id, String name);
    User getUser(String id);
	void save(User user);
}


