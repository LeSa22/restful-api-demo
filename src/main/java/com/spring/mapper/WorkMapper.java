package com.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.spring.model.Work;
import com.spring.service.WorkCreateUpdateRequest;

@Mapper
public interface WorkMapper {

    Work toEntity(WorkCreateUpdateRequest request);
    void updateEntity(WorkCreateUpdateRequest request, @MappingTarget Work entity);

}
