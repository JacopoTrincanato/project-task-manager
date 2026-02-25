package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<ContentPagination<ProjectResponseDTO>> getProjects(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		return new ResponseEntity<>(projectService.findAll(pageNo, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectDetails(@PathVariable("id") Long id) {

		ProjectResponseDTO project = projectService.findById(id);
		return new ResponseEntity<>(project, HttpStatus.OK);

	}
}
