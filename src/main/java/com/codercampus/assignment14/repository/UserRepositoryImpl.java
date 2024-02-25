package com.codercampus.assignment14.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codercampus.assignment14.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}
}
