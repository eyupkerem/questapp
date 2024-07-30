package com.project.questapp.service;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User user) {

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

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}