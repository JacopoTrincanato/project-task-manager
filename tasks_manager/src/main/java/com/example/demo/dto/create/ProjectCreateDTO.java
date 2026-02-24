package com.example.demo.dto.create;

import java.time.LocalDate;

public record ProjectCreateDTO(
		String name, 
		String description, 
		LocalDate creationDate,
		Long taskId,
		Long userId
		) {}
