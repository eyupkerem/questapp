package com.project.questapp.controller;

import com.project.questapp.entities.User;
import com.project.questapp.requests.UserRequest;
import com.project.questapp.security.JwtTokenProvider;
import com.project.questapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public String login(@RequestBody UserRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword());
        System.out.println(authToken);
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return  "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest){
        if (userService.getOneUserByUserName(registerRequest.getUsername()) != null){
            return new ResponseEntity<>("Username already in use" , HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword( passwordEncoder.encode(registerRequest.getPassword()));
        userService.save(user);
        System.out.println("User : " + user.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User successfully registered");
    }
}
