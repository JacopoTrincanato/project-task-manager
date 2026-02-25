package com.example.demo.exceptions_errors.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions_errors.ErrorResponse;
import com.example.demo.exceptions_errors.ProjectNotFoundException;
import com.example.demo.exceptions_errors.TaskNotFoundException;
import com.example.demo.exceptions_errors.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<?> handleProjectNotFoundException(ProjectNotFoundException exception) {
		ErrorResponse projectNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Project not found");
		return new ResponseEntity<>(projectNotFound, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException exception) {
		ErrorResponse taskNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Task not found");
		return new ResponseEntity<>(taskNotFound, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
		ErrorResponse userNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "User not found");
		return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
	}
}
