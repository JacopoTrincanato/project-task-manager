package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;

public record TaskDTO(
		String title,
		String description,
		Status status,
		Priority priority,
		LocalDate deadLine
		) {}
