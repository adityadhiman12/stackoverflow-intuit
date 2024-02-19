package com.intuit.stackoverflow.service;

import com.intuit.stackoverflow.model.User;

public interface UserService {
    User createUser(User user);

    User saveUser(User user);
    void deleteUser(Integer userId);
    User updateUsername(Integer userId, String newUsername);
}
