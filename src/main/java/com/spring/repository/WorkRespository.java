package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.spring.model.Work;

@Repository
public interface WorkRespository extends JpaRepository<Work, Long>, QuerydslPredicateExecutor<Work>{

}
