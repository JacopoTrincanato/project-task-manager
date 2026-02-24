package com.example.demo.mappers;

import com.example.demo.dto.response.TaskResponseDTO;
import com.example.demo.models.Task;

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
}
