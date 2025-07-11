package com.example.education.service;

import com.example.education.dao.entity.course.Course;
import com.example.education.dao.entity.material.Material;
import com.example.education.dao.repository.CourseRepository;
import com.example.education.dao.repository.MaterialRepository;
import com.example.education.dao.repository.QuestionRepository;
import com.example.education.dto.request.course.CourseRequest;
import com.example.education.dto.response.base.SuccessResponse;
import com.example.education.dto.response.course.CourseResponse;
import com.example.education.dto.response.material.MaterialResponse;
import com.example.education.enums.ResponseCode;
import com.example.education.mapper.CourseMapper;
import com.example.education.mapper.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final MaterialRepository materialRepository;
    private final QuestionRepository questionRepository;

    public SuccessResponse<CourseResponse> getCourseById(Long id){
        Course course = courseRepository.findCourseById(id).orElseThrow(() -> new RuntimeException("Course not found..." ));
        CourseResponse courseResponse= CourseMapper.INSTANCE.entityToResponse(course);
        List<Material> materials =materialRepository.findByCourseId(id);
        List<MaterialResponse> materialResponses = materials.stream()
                .map(MaterialMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
        courseResponse.setMaterials(materialResponses);
        return SuccessResponse.createSuccessResponse(courseResponse, ResponseCode.SUCCESS);
    }

    public SuccessResponse<CourseResponse> createCourse(CourseRequest courseRequest){
        Course course=CourseMapper.INSTANCE.requestToEntity(courseRequest);
        courseRepository.save(course);
        return SuccessResponse.createSuccessResponse(CourseMapper.INSTANCE.entityToResponse(course), ResponseCode.SUCCESS);
    }
}
