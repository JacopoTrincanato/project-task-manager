package com.example.demo.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ProjectResponseDTO(
		Long id,
		String name, 
		String description, 
		LocalDate creationDate,
		Long taskEseguiti,
		Long taskInEsecuzione,
		Long taskDaEseguire
		) {}
