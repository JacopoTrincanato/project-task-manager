package com.example.demo.mappers;

import java.util.List;
import java.util.Set;

import com.example.demo.dto.create.ProjectCreateDTO;
import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;

public class ProjectMapper {
	
	public static ProjectResponseDTO mapToProjectResponseDto(
			Project project,
			int done, 
			int inProgress,
			int todo) {

		ProjectResponseDTO projectDTO = new ProjectResponseDTO(
				project.getId(),
				project.getName(), 
				project.getDescription(),
				project.getCreationDate(), 
				done,
				inProgress,
				todo
				);
		
		return projectDTO;
	}
	
	public static Project mapToProject(
	        ProjectCreateDTO createDto,
	        List<Task> tasks,
	        Set<User> users
	){
		Project p = new Project();

	    p.setName(createDto.name());
	    p.setDescription(createDto.description());
	    p.setCreationDate(createDto.creationDate());

	    p.setTasks(tasks);
	    p.setUsers(users);

	    if (tasks != null) {
	        tasks.forEach(task -> task.setProject(p));
	    }

	    return p;
    }
}
