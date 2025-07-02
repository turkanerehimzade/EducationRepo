package com.example.education.validation;
import com.example.education.dao.repository.UserRepository;
import com.example.education.dto.request.UserRequest;
import com.example.education.enums.Regex;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public void validateUser(UserRequest userRequest) {

        List<String> messages = new ArrayList<>();

        // email validation
        if (userRequest.getEmail() == null) {
            messages.add("Email is required");
        } else if (userRequest.getEmail().isBlank()) {
            messages.add("Email can not be empty");
        } else if (!Pattern.matches(Regex.EMAIL_REGEX.getValidation(), userRequest.getEmail())) {
            messages.add("Email does not match");
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            messages.add("Used email");
        }

        // password validation
        if (userRequest.getPassword() == null) {
            messages.add("Password is required");
        } else if (userRequest.getPassword().isBlank()) {
            messages.add("Password can not be empty");
        }
        if (!Pattern.matches(Regex.PASSWORD_REGEX.getValidation(), userRequest.getPassword())) {
            messages.add("Password does not match");
        }

        if(!messages.isEmpty()){
            throw new ValidationException(String.valueOf(messages));
        }
    }
}
