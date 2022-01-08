package com.epam.customerservice.controller;

import com.epam.customerservice.dto.UserDto;
import com.epam.customerservice.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Long createUser() {
        return userService.createUser();
    }

    @DeleteMapping("/{sessionId}")
    public void deleteUser(@PathVariable("sessionId") long sessionId) {
        userService.deleteUser(sessionId);
    }

    @GetMapping("/userInfo/{userInfoId}")
    public UserDto getUserInfo(@PathVariable("userInfoId") long userInfoId) {
        return userService.getUser(userInfoId);
    }

    @PostMapping("/userInfo")
    public UserDto registerUserInfo(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @DeleteMapping("/userInfo/{userInfoId}")
    public void deleteUserInfo(@PathVariable long userInfoId) {
        userService.deleteUserInfo(userInfoId);
    }

    @PutMapping("/{sessionId}/userInfo/{userInfoId}")
    public void putUserInfoToSessionID(@PathVariable("sessionId") long sessionId, @PathVariable("userInfoId") long userInfoId) {
        userService.putRegisteredUserToUser(sessionId, userInfoId);
    }

    @GetMapping("/{sessionId}/userInfo")
    public UserDto getUserInfoBySessionId(@PathVariable("sessionId") long sessionId) {
        return userService.getRegisteredUserBySessionID(sessionId);
    }
}
