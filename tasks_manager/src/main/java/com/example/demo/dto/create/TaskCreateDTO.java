package com.example.demo.dto.create;

import java.time.LocalDate;

import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;

public record TaskCreateDTO(
	    String title,
	    String description,
	    Status status,
	    Priority priority,
	    LocalDate deadLine,
	    Long projectId,
	    Long userId
	) {}
