package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.create.UserCreateDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.exceptions_errors.UserNotFoundException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Role;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utilities.BaseService;

import jakarta.transaction.Transactional;

@Service
public class UserService implements BaseService<UserResponseDTO, UserCreateDTO, Long> {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	@Transactional
	public UserResponseDTO create(UserCreateDTO dto) {

		List<Task> tasks = dto.taskIds() != null
		        ? taskRepo.findAllById(dto.taskIds())
		        : new ArrayList<>();

		Set<Role> roles = dto.roleIds() != null
		        ? new HashSet<>(roleRepo.findAllById(dto.roleIds()))
		        : new HashSet<>();
		
		User user = UserMapper.matToUser(dto, roles, tasks);
		User saved = userRepo.save(user);

		return UserMapper.mapToUserResponseDto(saved);
	}
	
	@Override
	@Transactional
	public UserResponseDTO update(UserCreateDTO dto, Long id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow(
				() -> new UserNotFoundException("User not Found with id: " + id)
				);
		
		user.setUsername(dto.username());
		user.setPassword(dto.password());
		
		List<Task> tasks = dto.taskIds() != null
		        ? taskRepo.findAllById(dto.taskIds())
		        : new ArrayList<>();

		Set<Role> roles = dto.roleIds() != null
		        ? new HashSet<>(roleRepo.findAllById(dto.roleIds()))
		        : new HashSet<>();

		user.setTasks(tasks);
		user.setRoles(roles);
		
		User saved = userRepo.save(user);

		return UserMapper.mapToUserResponseDto(saved);
	}

	@Override
	public UserResponseDTO findById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow(
				() -> new UserNotFoundException("User not Found with id: " + id)
				);

	    return UserMapper.mapToUserResponseDto(user);
	}

	@Override
	public ContentPagination<UserResponseDTO> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> users = userRepo.findAll(pageable);
		List<User> listOfUsers = users.getContent();
		List<UserResponseDTO> content = listOfUsers.stream()
				.map(UserMapper::mapToUserResponseDto)
				.toList();
		
		ContentPagination<UserResponseDTO> projectResponse = new ContentPagination<>();
		projectResponse.setContent(content);
		projectResponse.setPageNo(users.getNumber());
		projectResponse.setPageSize(users.getSize());
		projectResponse.setTotalElements(users.getTotalElements());
		projectResponse.setTotalPages(users.getTotalPages());
		projectResponse.setLast(users.isLast());
		
		return projectResponse;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		if (!userRepo.existsById(id)) throw new UserNotFoundException("User not Found with id: " + id);
		userRepo.deleteById(id);
	}
	
	public UserResponseDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username).orElseThrow(
				() -> new UserNotFoundException("User not Found with username: " + username)
				);

		return UserMapper.mapToUserResponseDto(user);
	}
}
