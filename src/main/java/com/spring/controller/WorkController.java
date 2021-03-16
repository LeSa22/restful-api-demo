package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Work;
import com.spring.service.WorkService;
import com.spring.utils.Paging;
import com.spring.utils.ResponseUtil;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
public class WorkController {

	@Autowired
	private final WorkService workService;
    
    @GetMapping(value = "/")
    @ApiOperation("Get list work with pagination")
    public ResponseEntity<Paging<Work>> getAllWorks(Pageable pageable) {
        return ResponseUtil.wrap(workService.getAll(pageable), HttpStatus.OK);
    }
}
