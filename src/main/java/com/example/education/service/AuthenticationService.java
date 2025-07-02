package com.example.education.service;

import com.example.education.dao.entity.User;
import com.example.education.dao.repository.UserRepository;
import com.example.education.dto.request.auth.ChangePasswordRequest;
import com.example.education.dto.request.auth.SignInRequest;
import com.example.education.dto.response.user.UserLoginResponse;
import com.example.education.security.UserPrincipal;
import com.example.education.security.jwt.JwtProvider;
import com.example.education.validation.AuthValidation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;



@Service

public class AuthenticationService {
    private final AuthValidation authValidation;
    private final UserRepository userRepository;
    @Value("${authentication.jwt.access.expiration-in-ms}")
    private  Long JWT_ACCESS_EXPIRATION_IN_MS;
    @Value("${authentication.jwt.refresh.expiration-in-ms}")
    private Long JWT_REFRESH_EXPIRATION_IN_MS;

    private final AuthenticationManager authenticationManager;
    private  final JwtProvider jwtProvider;
    private final AuthTokenService authTokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtProvider jwtProvider, AuthTokenService authTokenService, AuthValidation authValidation, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.authTokenService = authTokenService;
        this.authValidation = authValidation;
        this.userRepository = userRepository;
    }

    private UserLoginResponse getLoginResponse(UserPrincipal userPrincipal) {
        String accessToken = jwtProvider.generateToken(userPrincipal, JWT_ACCESS_EXPIRATION_IN_MS);
        String refreshToken=jwtProvider.generateToken(userPrincipal, JWT_REFRESH_EXPIRATION_IN_MS);
        System.out.println(accessToken);
        System.out.println(refreshToken);
        authTokenService.saveTokenInfo(userPrincipal,accessToken,refreshToken);
        return new UserLoginResponse().setAccessToken(accessToken).setRefreshToken(refreshToken);
    }
    public ResponseEntity<UserLoginResponse> signInAndReturnJWT(SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(getLoginResponse(userPrincipal));
    }
    public ResponseEntity<Object> logout(String accessToken) {
        authTokenService.deactiveAccessToken(accessToken);
        return ResponseEntity.ok(null);

    }
    public ResponseEntity<UserLoginResponse> refreshToken(HttpServletRequest authorizationHeader){

        Authentication authentication= jwtProvider.getAuthentication(authorizationHeader);
        return ResponseEntity.ok(getLoginResponse((UserPrincipal) authentication.getPrincipal()));
    }

    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest){
        authValidation.validateChangePassword(changePasswordRequest);
        User user=userRepository.findByEmail(changePasswordRequest.getEmail()).orElse(null);
        if(user.getPassword().equals(changePasswordRequest.getNewPassword())){
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepository.save(user);
            return ResponseEntity.ok("Password changed");
        }
        return ResponseEntity.badRequest().body("Wrong password");
    }
}
