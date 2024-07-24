package com.project.questapp.controller;

import com.project.questapp.entities.User;
import com.project.questapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping()
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId , @RequestBody User user){

        return userService.updateUser(userId,user);

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }


}
