package com.example.education.service;

import com.example.education.dao.entity.material.Material;
import com.example.education.dao.entity.question.Question;
import com.example.education.dao.repository.MaterialRepository;
import com.example.education.dao.repository.QuestionRepository;
import com.example.education.dto.request.question.QuestionRequest;
import com.example.education.dto.response.base.SuccessResponse;
import com.example.education.dto.response.question.QuestionResponse;
import com.example.education.enums.ResponseCode;
import com.example.education.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MaterialRepository materialRepository;
    public SuccessResponse<QuestionResponse> addQuestion(QuestionRequest questionRequest) {
        Material material = materialRepository.findById(questionRequest.getMaterialId()).orElseThrow(() -> new RuntimeException("Material not found"));
        Question question = QuestionMapper.INSTANCE.requestToEntity(questionRequest);
        question.setMaterial(material);
        questionRepository.save(question);
        return SuccessResponse.createSuccessResponse(QuestionMapper.INSTANCE.entityToResponse(question), ResponseCode.SUCCESS );

    }
//    public SuccessResponse<List<QuestionResponse>> getQuestion(Long materialId) {
//        List<Question> questions = questionRepository.findByMaterialId(materialId)
//                .orElseThrow(() -> new RuntimeException("Material not found"));
//
//        List<QuestionResponse> questionResponses = questions.stream()
//                .map(QuestionMapper.INSTANCE::entityToResponse)
//                .collect(Collectors.toList());
//
//        return SuccessResponse.createSuccessResponse(questionResponses, ResponseCode.SUCCESS);
//    }

}