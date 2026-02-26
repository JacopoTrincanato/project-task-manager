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
import com.example.demo.models.LoginRequest;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
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
	
	@PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO user) {

        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
    public ResponseEntity<String> createUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {

        try {
        	boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        	
        	if (isAuthenticated) {
        		session.setAttribute("USER", loginRequest.getUsername());
        		return new ResponseEntity<>("Login was successful", HttpStatus.OK);
        	} else {
        		return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        	}
        } catch (Exception e) {
        	return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserCreateDTO user, @PathVariable Long id) {

        return new ResponseEntity<>(userService.update(user, id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {

    	userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

