package com.example.demo.exceptions_errors;

public class ProjectNotFoundException extends RuntimeException {

	public ProjectNotFoundException(String message) {
		super(message);
	}
}
