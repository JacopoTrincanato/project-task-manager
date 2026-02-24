package com.example.demo.dto;

import java.util.List;
import java.util.Set;

public record UserDTO(
		String username,
		Set<ProjectDTO> projects,
		List<TaskDTO> tasks 
		) {

}
