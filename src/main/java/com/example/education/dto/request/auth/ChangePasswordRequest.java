package com.example.education.dto.request.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChangePasswordRequest {
    private String email;
    private String newPassword;
    private String confirmPassword;
}
