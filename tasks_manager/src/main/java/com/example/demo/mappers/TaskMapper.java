package com.example.demo.mappers;

import com.example.demo.dto.TaskDTO;
import com.example.demo.models.Task;

public class TaskMapper {
	public static TaskDTO mapToTaskDto(Task task) {
		TaskDTO taskDTO = new TaskDTO(
				task.getTitle(), 
				task.getDescription(), 
				task.getStatus().toString(),
				task.getPriority().toString(),
				task.getDeadLine());
		return taskDTO;
	}
}
