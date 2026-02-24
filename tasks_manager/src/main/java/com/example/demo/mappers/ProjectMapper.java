package com.example.demo.mappers;

import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.models.Project;

public class ProjectMapper {
	public static ProjectResponseDTO mapToProjectResponseDto(Project project) {

		ProjectResponseDTO projectDTO = new ProjectResponseDTO(
				project.getId(),
				project.getName(), 
				project.getDescription(),
				project.getCreationDate(), 
				null, 
				null,
				null
				);
		
		return projectDTO;
	}
}
