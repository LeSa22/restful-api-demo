package com.spring.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.spring.model.Work;
import com.spring.utils.Paging;

public interface WorkService {

	Paging<Work> getAll(Pageable pageable);
	Work create(@Valid WorkCreateUpdateRequest request);
    Work update(Long id, @Valid WorkCreateUpdateRequest request);
    Work save(Work loaiRuiRo);
    void delete(Long id);
}
