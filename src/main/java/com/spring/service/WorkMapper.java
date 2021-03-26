package com.spring.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.spring.model.Work;

@Mapper
public interface WorkMapper {
    Work toEntity(WorkCreateUpdateRequest request);
    void updateEntity(WorkCreateUpdateRequest request, @MappingTarget Work entity);

}
