package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.create.ProjectCreateDTO;
import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.services.ProjectService;

import jakarta.validation.Valid;

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
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable("id") @Valid Long id) {

		ProjectResponseDTO project = projectService.findById(id);
		return new ResponseEntity<>(project, HttpStatus.OK);

	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<?> getProjectByTitle(@PathVariable("title") @Valid String title) {

		ProjectResponseDTO project = projectService.findByTitle(title);
		return new ResponseEntity<>(project, HttpStatus.OK);

	}
	
	@PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectCreateDTO project) {

        return new ResponseEntity<>(projectService.create(project), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectCreateDTO project, @Valid @PathVariable Long id) {

        return new ResponseEntity<>(projectService.update(project, id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@Valid @PathVariable Long id) {

        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
