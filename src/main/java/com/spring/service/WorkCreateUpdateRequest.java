package com.spring.service;

import java.time.LocalDateTime;

import com.spring.enums.StatusWorkEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * dto request
 * @author sattl
 *
 */
@Data
@AllArgsConstructor
public class WorkCreateUpdateRequest {
	
	private boolean deleted;
    
	private String workName;
	
	private LocalDateTime startingDate;
	private LocalDateTime endingDate;
	@ApiModelProperty
	private StatusWorkEnum status;
}
