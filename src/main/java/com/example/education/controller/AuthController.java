package com.example.education.controller;

import com.example.education.dto.request.auth.ChangePasswordRequest;
import com.example.education.dto.request.auth.SignInRequest;
import com.example.education.dto.response.user.UserLoginResponse;
import com.example.education.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@SecurityRequirement(name = "Authorization")
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> logout(@RequestHeader("Authorization")String accessToken){
        return authenticationService.logout(accessToken.substring(7));

    }
    @PostMapping("/refresh-token")
    public ResponseEntity<UserLoginResponse> refreshToken( HttpServletRequest authorizationHeader){
        return authenticationService.refreshToken(authorizationHeader);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<UserLoginResponse> signIn(@RequestBody SignInRequest request){
        return authenticationService.signInAndReturnJWT(request);
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return authenticationService.changePassword(changePasswordRequest);
    }
}
