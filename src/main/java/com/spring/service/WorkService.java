package com.spring.service;

import org.springframework.data.domain.Pageable;

import com.spring.model.Work;
import com.spring.utils.Paging;

public interface WorkService {

	Paging<Work> getAll(Pageable pageable);
}
