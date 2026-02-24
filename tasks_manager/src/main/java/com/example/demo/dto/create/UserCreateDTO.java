package com.example.demo.dto.create;

import java.util.List;
import java.util.Set;

import com.example.demo.models.Role;
import com.example.demo.models.Task;

public record UserCreateDTO(
		String username,
		String password,
		Set<Role> roles,
		List<Task> tasks
		) {

}
