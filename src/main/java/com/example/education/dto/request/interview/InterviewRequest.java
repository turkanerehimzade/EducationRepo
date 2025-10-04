package com.example.education.dto.request.interview;

import com.example.education.enums.Level;
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
    private Level level;
    private String category;
    private String degree;
}
