package com.spring.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spring.model.QWork;
import com.spring.model.Work;
import com.spring.repository.WorkRespository;
import com.spring.utils.Paging;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService{
	
	private WorkRespository repo;
	
	@Override
	public Paging<Work> getAll(Pageable pageable) {
		BooleanExpression query = QWork.work.deleted.isFalse();
        BooleanBuilder expression = new BooleanBuilder(query);
        return Paging.of(repo.findAll(expression, pageable));
	}

}
