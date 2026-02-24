package com.example.demo.dto;

import java.time.LocalDate;

//import com.example.demo.enums.Priority;
//import com.example.demo.enums.Status;

public record TaskDTO(
		String title,
		String description,
		String status,
		String priority,
		LocalDate deadLine
		) {}
