package com.example.education.mapper;

import com.example.education.dao.entity.question.Question;
import com.example.education.dto.request.question.QuestionRequest;
import com.example.education.dto.response.question.QuestionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
    QuestionResponse entityToResponse(Question question);
    Question requestToEntity(QuestionRequest questionRequest);
}
