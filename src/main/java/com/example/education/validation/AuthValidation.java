package com.example.education.validation;

import com.example.education.dao.repository.UserRepository;
import com.example.education.dto.request.auth.ChangePasswordRequest;
import com.example.education.enums.Regex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AuthValidation {
    private final UserRepository userRepository;

    public void validateChangePassword(ChangePasswordRequest changePasswordRequest) {
        List<String> messages = new ArrayList<>();
        if (changePasswordRequest.getOldPassword().equals(changePasswordRequest.getNewPassword())) {
            messages.add("Old password does not match");
        } else if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            messages.add("New password does not match");
        }else if (changePasswordRequest.getNewPassword().isBlank()) {
            messages.add("Password can not be empty");
        } else if(!Pattern.matches(Regex.PASSWORD_REGEX.getValidation(), changePasswordRequest.getNewPassword())) {
            messages.add("Password does not match");
        } else if (!userRepository.existsByEmail(changePasswordRequest.getEmail())) {
            messages.add("Mail address does not exist");
        }
    }
}
