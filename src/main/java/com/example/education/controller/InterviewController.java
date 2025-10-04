package com.example.education.controller;

import com.example.education.dto.request.interview.InterviewRequest;
import com.example.education.dto.response.base.SuccessResponse;
import com.example.education.dto.response.interview.InterviewResponse;
import com.example.education.enums.Level;
import com.example.education.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/interview")
public class InterviewController {
    private final InterviewService interviewService;
//    @GetMapping
//    public SuccessResponse<List<InterviewResponse>> getInterviews(@RequestParam Level level ) {
//        return interviewService.getInterviews(level);
//    }
    @PostMapping
    public SuccessResponse<String> createInterviewQuestion(@RequestBody InterviewRequest interviewRequest) {
        return interviewService.createInterviewQuestion(interviewRequest);
    }
//    public SuccessResponse<List<InterviewResponse>> getInterviews() {
//        return interviewService.getAllInterviews();
//    }
////    @PostMapping
////    public SuccessResponse<String> createInterviewQuestion(InterviewRequest interviewRequest) {
////        return interviewService.createInterviewQuestion(interviewRequest);
////    }
}
