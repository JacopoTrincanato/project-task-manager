package com.example.demo.mappers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Project;

public class ProjectMapper {
	public static ProjectDTO mapToProjectDto(Project project) {
		Set<UserDTO> usersDto = project.getUsers().stream()
				.map(UserMapper::mapToUserDto)
				.collect(Collectors.toSet());
		
		List<TaskDTO> tasksDto = project.getTasks().stream()
				.map(TaskMapper::mapToTaskDto)
				.toList();
		
		ProjectDTO projectDTO = new ProjectDTO(
				project.getName(), 
				project.getDescription(),
				project.getCreationDate(), 
				usersDto, 
				tasksDto
				);
		
		return projectDTO;
	}
}
