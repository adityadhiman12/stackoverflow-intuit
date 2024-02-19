package com.intuit.stackoverflow.controllers;

import com.intuit.stackoverflow.model.User;
import com.intuit.stackoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUserHandler(@RequestBody User user) {
        User createdUser= userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserHandler(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updateUsername/{userId}")
    public ResponseEntity<User> updateUsernameHandler(@PathVariable Integer userId, @RequestParam String newUsername) {
        User updatedUser = userService.updateUsername(userId, newUsername);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
