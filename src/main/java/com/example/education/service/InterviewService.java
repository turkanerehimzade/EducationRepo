package com.example.education.service;

import com.example.education.dao.entity.interview.Interview;
import com.example.education.dao.repository.InterviewRepository;
import com.example.education.dto.request.interview.InterviewRequest;
import com.example.education.dto.response.base.SuccessResponse;
import com.example.education.dto.response.interview.InterviewResponse;
import com.example.education.enums.Level;
import com.example.education.enums.ResponseCode;
import com.example.education.mapper.InterviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public SuccessResponse<List<InterviewResponse>> getInterviews(Level level) {

        List<Interview> interviews ;
        if (level==Level.NULL) {
            interviews=interviewRepository.findAll();
        } else{
            interviews=interviewRepository.findByLevel(level);
        }
        List<InterviewResponse> interviewResponses = interviews.stream()
                .map(InterviewMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
        return SuccessResponse.createSuccessResponse(interviewResponses, ResponseCode.SUCCESS);
    }

    public SuccessResponse<String> createInterviewQuestion(InterviewRequest interviewRequest) {
        Interview interview = InterviewMapper.INSTANCE.requestToEntity(interviewRequest);
        interviewRepository.save(interview);
        return SuccessResponse.createSuccessResponse("Success", ResponseCode.SUCCESS);
    }
}
