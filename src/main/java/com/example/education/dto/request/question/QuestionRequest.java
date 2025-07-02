package com.example.education.dto.request.question;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QuestionRequest {
    private Long materialId;
    private String question;
    private String answer;
}
