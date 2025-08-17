package com.example.education.controller;

import com.example.education.dto.request.course.CourseRequest;
import com.example.education.dto.response.PageResponse;
import com.example.education.dto.response.base.SuccessResponse;
import com.example.education.dto.response.course.CourseResponse;
import com.example.education.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/course")
public class CourseController {
    private final CourseService courseService;
    @GetMapping("/{id}")
    public SuccessResponse<CourseResponse> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @GetMapping
    public SuccessResponse<PageResponse<CourseResponse>> getAllCourses(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return courseService.getAllCourses(page,size);
    }

}
