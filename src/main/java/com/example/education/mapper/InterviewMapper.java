package com.example.education.mapper;

import com.example.education.dao.entity.interview.Interview;
import com.example.education.dto.request.interview.InterviewRequest;
import com.example.education.dto.response.interview.InterviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface InterviewMapper {
    InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);
    InterviewResponse entityToResponse(Interview interview);
    Interview requestToEntity(InterviewRequest interviewRequest);

}
