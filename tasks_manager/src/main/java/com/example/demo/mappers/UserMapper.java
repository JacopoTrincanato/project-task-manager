package com.example.demo.mappers;

import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.models.User;

public class UserMapper {
	public static UserResponseDTO mapToUserDto(User user) {
		
		UserResponseDTO userDTO = new UserResponseDTO(
				user.getId(),
				user.getUsername(),
				user.getRoles()
				);
		
		return userDTO;
	}
}
