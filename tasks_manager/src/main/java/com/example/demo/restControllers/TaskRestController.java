package com.example.demo.restControllers;

import java.util.List;

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
import com.example.demo.dto.create.TaskCreateDTO;
import com.example.demo.dto.response.TaskResponseDTO;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<ContentPagination<TaskResponseDTO>> getTasks(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		return new ResponseEntity<>(taskService.findAll(pageNo, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<TaskResponseDTO>> getTaskByStatus(
			@RequestParam(value = "status") Status status) {
		
		List<TaskResponseDTO> filteredTaskByStatus = taskService.findByStatus(status);
		return new ResponseEntity<>(filteredTaskByStatus, HttpStatus.OK);

	}
	
	@GetMapping("/priority")
	public ResponseEntity<List<TaskResponseDTO>> getTaskByPriority(
			@RequestParam(value = "priority") Priority priority) {
		
		List<TaskResponseDTO> filteredTaskByPriority = taskService.findByPriority(priority);
		return new ResponseEntity<>(filteredTaskByPriority, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Long id) {

		TaskResponseDTO task = taskService.findById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);

	}
	
	@PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskCreateDTO task) {

        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@Valid @RequestBody TaskCreateDTO task, @PathVariable Long id) {

        return new ResponseEntity<>(taskService.update(task, id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> deleteTask(@PathVariable Long id) {

    	taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
