package com.intuit.stackoverflow.service.impl;

import com.intuit.stackoverflow.model.User;
import com.intuit.stackoverflow.repository.UserRepository;
import com.intuit.stackoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken. Please choose a different one.");
        }
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        userRepository.delete(existingUser);
    }

    @Override
    public User updateUsername(Integer userId, String newUsername) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        if (userRepository.findByUsername(newUsername).isPresent()) {
            throw new RuntimeException("Username is already taken. Please choose a different one.");
        }
        existingUser.setUsername(newUsername);
        return userRepository.save(existingUser);
    }
}
