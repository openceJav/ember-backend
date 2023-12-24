package org.ember.emberbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.ember.emberbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.ember.emberbackend.models.User;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        service.saveUser(user);
        return user;
    }

    @MessageMapping("/user.removeUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        service.disconnectUser(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectionUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
