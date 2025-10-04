package com.example.education.dto.response.interview;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InterviewResponse {
    private String question;
    private String answer;
}
