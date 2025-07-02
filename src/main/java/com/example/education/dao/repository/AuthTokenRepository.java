package com.example.education.dao.repository;

import com.example.education.dao.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findAuthTokenByAccessTokenAndIsActive(String accessToken, Boolean isActive);
}
