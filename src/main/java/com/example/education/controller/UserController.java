package com.example.education.controller;

import com.example.education.service.AuthenticationService;
import com.example.education.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
     private final UserService userService;
     private final AuthenticationService authenticationService;

//     @PostMapping("/sign-up")
//     public SuccessResponse<UserResponse> addUser(@RequestBody UserRequest userRequest) {
//          return userService.createUser(userRequest);
//     }


}
