package com.project.questapp.controller;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    @PostMapping()
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId , @RequestBody User user){
        Optional<User> tempUser = userRepository.findById(userId);

        if (tempUser.isPresent()){
            User updatedUser = tempUser.get();
            updatedUser.setUserName(user.getUserName());
            updatedUser.setPassword(user.getPassword());
            userRepository.save(updatedUser);
            return updatedUser;
        }
        return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }


}
