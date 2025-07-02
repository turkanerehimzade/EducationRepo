package com.example.education.dto.request.course;

import com.example.education.dao.entity.material.Material;
import com.example.education.dto.request.material.MaterialRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
public class CourseRequest {
    private String title;
    private String description;
}
