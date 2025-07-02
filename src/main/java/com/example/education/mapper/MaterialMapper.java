package com.example.education.mapper;

import com.example.education.dao.entity.material.Material;
import com.example.education.dto.request.material.MaterialRequest;
import com.example.education.dto.response.material.MaterialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);
    MaterialResponse entityToResponse(Material material);
    Material entityToRequest(MaterialRequest materialRequest);
}
