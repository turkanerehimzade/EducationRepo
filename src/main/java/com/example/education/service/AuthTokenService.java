package com.example.education.service;

import com.example.education.dao.entity.AuthToken;
import com.example.education.dao.repository.AuthTokenRepository;
import com.example.education.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;

    public void deactiveAccessToken(String accessToken) {
        AuthToken authToken = authTokenRepository.findAuthTokenByAccessTokenAndIsActive(accessToken, true)
                .orElseThrow(() -> new RuntimeException("Auth token not found"));
        authToken.setIsActive(false);
        authTokenRepository.save(authToken);
    }

    private String encryptAccessToken(String accessToken) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(accessToken.getBytes());
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void checkAccessToken(String accessToken) {
        String token = encryptAccessToken(accessToken);
        System.err.println(token);
        authTokenRepository.findAuthTokenByAccessTokenAndIsActive(token, true)
                .orElseThrow(() -> new RuntimeException("Auth token not found"));
    }

    public void saveTokenInfo(UserPrincipal userPrincipal, String accessToken, String refreshToken) {
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(accessToken);
        authToken.setRefreshToken(refreshToken);
        authToken.setUserPrincipal(userPrincipal);
        authTokenRepository.save(authToken);
    }

}
