package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spring.enums.ApiErrorEnum;
import com.spring.enums.StatusWorkEnum;
import com.spring.error.BadRequestAlertException;
import com.spring.error.ValidateError;
import com.spring.error.ValidationErrorException;
import com.spring.model.QWork;
import com.spring.model.Work;
import com.spring.repository.WorkRespository;
import com.spring.utils.Paging;

@Service
@Transactional
public class WorkServiceImpl implements WorkService{
	
	private static final BooleanExpression BASE_PREDICATE = QWork.work.deleted.isFalse();
	
	@Autowired
	private WorkRespository repo;
	@Autowired
	private WorkMapper workMapper;
	
	@Override
	public Paging<Work> getAll(Pageable pageable) {
		BooleanExpression query = BASE_PREDICATE;
        BooleanBuilder expression = new BooleanBuilder(query);
        return Paging.of(repo.findAll(expression, pageable));
	}

	@Override
	public Work create(@Valid WorkCreateUpdateRequest request) {
		List<ValidateError> errors = new ArrayList<>();
		if (request.getStatus() != null) {
			StatusWorkEnum status = StatusWorkEnum.valueOf(request.getStatus().toString());
			if (status == null) {
				errors.add(new ValidateError("Status work", ApiErrorEnum.NOT_FOUND_ENUM.getText()));
			}
	    }
        if (!errors.isEmpty()) {
            throw new ValidationErrorException(Work.class.getName(), Status.BAD_REQUEST, errors);
        }
        return repo.save(workMapper.toEntity(request));
	}

	@Override
	public Work update(Long id, @Valid WorkCreateUpdateRequest request) {
		Work entity = repo.findOne(BASE_PREDICATE.and(QWork.work.id.eq(id)))
				.orElseThrow(() -> new BadRequestAlertException(ApiErrorEnum.NOT_FOUND_ID.getText(), Work.class.getName(), "Id"));
        workMapper.updateEntity(request, entity);
        return repo.save(entity);
	}

	@Override
	public Work save(Work loaiRuiRo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Work work = repo.findOne(BASE_PREDICATE.and(QWork.work.id.eq(id)))
				.orElseThrow(() -> new BadRequestAlertException(ApiErrorEnum.NOT_FOUND_ID.getText(), Work.class.getName(), "Id"));
    	work.setDeleted(true);
        repo.save(work);
	}

}
