package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ProjectDTO(
		String name, 
		String description, 
		LocalDate creationDate,
		Set<UserDTO> users,
		List<TaskDTO> tasks
		) {}
