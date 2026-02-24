package com.example.demo.dto.create;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ProjectCreateDTO(
		String name, 
		String description, 
		LocalDate creationDate,
		List<Long> taskIds,
		Set<Long> userIds
		) {}
