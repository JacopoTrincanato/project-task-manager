package com.example.demo.dto.response;

import java.time.LocalDate;

import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;

public record TaskResponseDTO(
		Long id,
		String title,
		String description,
		Status status,
		Priority priority,
		LocalDate deadLine
		) {}
