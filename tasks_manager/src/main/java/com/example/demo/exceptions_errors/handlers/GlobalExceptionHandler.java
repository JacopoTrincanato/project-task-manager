package com.example.demo.exceptions_errors.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions_errors.ErrorResponse;
import com.example.demo.exceptions_errors.ProjectNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<?> handleProjectNotFoundException(ProjectNotFoundException exception) {
		ErrorResponse projectNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Project not found");
		return new ResponseEntity<>(projectNotFound, HttpStatus.NOT_FOUND);
	}
}
