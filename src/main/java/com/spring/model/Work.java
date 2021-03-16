package com.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.spring.enums.StatusWorkEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work")
public class Work {

	private String workName;
	
	private LocalDateTime startingDate;
	private LocalDateTime endingDate;
	
	@Enumerated(EnumType.STRING)
	private StatusWorkEnum status;
}
