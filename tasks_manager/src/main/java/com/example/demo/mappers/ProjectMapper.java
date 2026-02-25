package com.example.demo.mappers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.create.ProjectCreateDTO;
import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.enums.Status;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.services.TaskService;

public class ProjectMapper {
	
	@Autowired
	private static TaskService taskService;
	
	public static ProjectResponseDTO mapToProjectResponseDto(Project project) {

		ProjectResponseDTO projectDTO = new ProjectResponseDTO(
				project.getId(),
				project.getName(), 
				project.getDescription(),
				project.getCreationDate(), 
				taskService.taskCountByStatus(Status.DONE), 
				taskService.taskCountByStatus(Status.IN_PROGRESS),
				taskService.taskCountByStatus(Status.TODO)
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
