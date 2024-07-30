package com.project.questapp.service;

import com.project.questapp.entities.User;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JwtUserDetails.create(user);
    }
    public UserDetails loadUserById(Long id){
        User user=userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
