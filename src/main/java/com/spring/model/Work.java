package com.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.spring.enums.StatusWorkEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "work")
public class Work {

	@Id
    @GeneratedValue
    private Long id;
	
	private boolean deleted;
    
	private String workName;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime startingDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime endingDate;
	
	@Enumerated(EnumType.STRING)
	private StatusWorkEnum status;
}
