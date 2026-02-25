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
import com.example.demo.dto.create.UserCreateDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<ContentPagination<UserResponseDTO>> getUsers(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		return new ResponseEntity<>(userService.findAll(pageNo, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {

		UserResponseDTO task = userService.findById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);

	}
	
	@PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO task) {

        return new ResponseEntity<>(userService.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserCreateDTO task, @PathVariable Long id) {

        return new ResponseEntity<>(userService.update(task, id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {

    	userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

