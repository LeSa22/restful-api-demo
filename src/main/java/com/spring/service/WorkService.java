package com.spring.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.spring.model.Work;
import com.spring.utils.Paging;

public interface WorkService {

	Paging<Work> getAll(Pageable pageable);
	Work create(@Valid WorkCreateUpdateRequest request);
	Optional<Work> getById(@Valid Long id);
    Work update(@Valid Long id, WorkCreateUpdateRequest request);
    void delete(Long id);
}
