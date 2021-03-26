package com.spring.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Work;
import com.spring.service.WorkCreateUpdateRequest;
import com.spring.service.WorkService;
import com.spring.utils.Paging;
import com.spring.utils.ResponseUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/work")
public class WorkController {

	@Autowired
	private WorkService workService;
    
    @GetMapping(value = "/")
    @ApiOperation("Get list work with pagnation")
    public ResponseEntity<Paging<Work>> getAllWorks(Pageable pageable) {
        return ResponseUtil.wrap(workService.getAll(pageable), HttpStatus.OK);
    }
    
    @PostMapping(value = "/")
    @ApiOperation("Create work")
    public ResponseEntity<Work> create(@Valid @RequestBody WorkCreateUpdateRequest request) {
        return ResponseUtil.wrap(workService.create(request), HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    @ApiOperation("Update work")
    public ResponseEntity<Void> update(@PathVariable Long id, 
            @Valid @RequestBody WorkCreateUpdateRequest request) {
        workService.update(id, request);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping(value = "/{id}")
    @ApiOperation("Get work by id")
    public ResponseEntity<Optional<Work>> getWorkById(@PathVariable Long id) {
        return ResponseEntity.ok(workService.getById(id)) ;
    }
    
    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete work")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	workService.delete(id);
        return ResponseEntity.ok().build();
    }
}
