package com.example.education.dto.request.interview;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InterviewRequest {
    private String question;
    private String answer;
    private String category;
    private String degree;
}
