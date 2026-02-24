package com.example.demo.dto.response;

import java.util.Set;

import com.example.demo.models.Role;

public record UserResponseDTO(
		Long id,
		String username,
		Set<Role> roles 
		) {

}
