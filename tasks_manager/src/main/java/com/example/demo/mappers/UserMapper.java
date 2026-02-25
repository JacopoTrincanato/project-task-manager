package com.example.demo.mappers;

import java.util.List;
import java.util.Set;

import com.example.demo.dto.create.UserCreateDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.models.Role;
import com.example.demo.models.Task;
import com.example.demo.models.User;

public class UserMapper {
	public static UserResponseDTO mapToUserResponseDto(User user) {
		
		UserResponseDTO userDTO = new UserResponseDTO(
				user.getId(),
				user.getUsername(),
				user.getRoles()
				);
		
		return userDTO;
	}
	
	public static User matToUser(
			UserCreateDTO createDto,
			Set<Role> roles,
			List<Task> tasks
			) {
		
		User user = new User();
		
		user.setUsername(createDto.username());
		user.setPassword(createDto.password());
		user.setRoles(roles);
		user.setTasks(tasks);
		
		if (tasks != null) {
	        tasks.forEach(task -> task.setUser(user));
	    }
		
		return user;
	}
}
