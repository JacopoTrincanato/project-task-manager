package com.example.demo.mappers;

import com.example.demo.dto.create.TaskCreateDTO;
import com.example.demo.dto.response.TaskResponseDTO;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;

public class TaskMapper {
	public static TaskResponseDTO mapToTaskDto(Task task) {
		TaskResponseDTO taskDTO = new TaskResponseDTO(
				task.getId(),
				task.getTitle(), 
				task.getDescription(), 
				task.getStatus(),
				task.getPriority(),
				task.getDeadLine());
		
		return taskDTO;
	}
	
	public static Task mapToTask(
			TaskCreateDTO createDto,
			Project project,
			User user
			) {
		
		Task t = new Task();
		
		t.setTitle(createDto.title());
		t.setDescription(createDto.description());
		t.setStatus(createDto.status());
		t.setPriority(createDto.priority());
		t.setDeadLine(createDto.deadLine());
		t.setProject(project);
		t.setUser(user);
		
		return t;
	}
}
