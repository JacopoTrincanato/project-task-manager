package com.example.demo.dto.response;

import java.time.LocalDate;

public record ProjectResponseDTO(
		Long id,
		String name, 
		String description, 
		LocalDate creationDate,
		Long taskEseguiti,
		Long taskInEsecuzione,
		Long taskDaEseguire
		) {}
