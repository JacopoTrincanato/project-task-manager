package com.example.demo.dto.create;

import java.util.List;
import java.util.Set;

public record UserCreateDTO(
		String username,
		String password,
		Set<Long> roleIds,
		List<Long> taskIds
		) {

}
