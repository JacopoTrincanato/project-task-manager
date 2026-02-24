package com.example.demo.mappers;

import java.util.List;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;

public class UserMapper {
	public static UserDTO mapToUserDto(User user) {
		List<TaskDTO> tasksDto = user.getTasks().stream()
				.map(TaskMapper::mapToTaskDto)
				.toList();
		
		UserDTO userDTO = new UserDTO(
				user.getUsername(),
				null,
				tasksDto
				);
		
		return userDTO;
	}
}
