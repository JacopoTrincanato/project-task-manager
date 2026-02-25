package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dto.create.TaskCreateDTO;
import com.example.demo.dto.response.TaskResponseDTO;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import com.example.demo.exceptions_errors.TaskNotFoundException;
import com.example.demo.mappers.TaskMapper;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utilities.BaseService;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

@Service
public class TaskService implements BaseService<TaskResponseDTO, TaskCreateDTO, Long> {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public TaskResponseDTO create(TaskCreateDTO dto) {
		// TODO Auto-generated method stub
		Project project = projectRepo.findById(dto.projectId()).orElse(null);
		User user = userRepo.findById(dto.userId()).orElse(null);
		
		Task task = TaskMapper.mapToTask(dto, project, user);
		Task saved = taskRepo.save(task);
		return TaskMapper.mapToTaskDto(saved);
	}

	@Override
	@Transactional
	public TaskResponseDTO update(TaskCreateDTO dto, Long id) {
		// TODO Auto-generated method stub
		Task task = taskRepo.findById(id).orElseThrow(
				() -> new TaskNotFoundException("Task not found with id " + id)
				);
		
		task.setTitle(dto.title());
		task.setDescription(dto.description());
		task.setStatus(dto.status());
		task.setPriority(dto.priority());
		task.setDeadLine(dto.deadLine());
		
		Project project = projectRepo.findById(dto.projectId()).orElse(null);
		User user = userRepo.findById(dto.userId()).orElse(null);
		
		task.setProject(project);
		task.setUser(user);
		
		Task saved = taskRepo.save(task);
		return TaskMapper.mapToTaskDto(saved);
	}

	@Override
	public TaskResponseDTO findById(Long id) {
		// TODO Auto-generated method stub
		Task task = taskRepo.findById(id).orElseThrow(
				() -> new TaskNotFoundException("Task not found with id " + id)
				);
		
		return TaskMapper.mapToTaskDto(task);
	}

	@Override
	public ContentPagination<TaskResponseDTO> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Task> tasks = taskRepo.findAll(pageable);
		List<Task> listOfTasks = tasks.getContent();
		List<TaskResponseDTO> content = listOfTasks.stream()
				.map(TaskMapper::mapToTaskDto)
				.toList();
		
		ContentPagination<TaskResponseDTO> taskResponse = new ContentPagination<>();
		taskResponse.setContent(content);
		taskResponse.setPageNo(tasks.getNumber());
		taskResponse.setPageSize(tasks.getSize());
		taskResponse.setTotalElements(tasks.getTotalElements());
		taskResponse.setTotalPages(tasks.getTotalPages());
		taskResponse.setLast(tasks.isLast());
		
		return taskResponse;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		if (!taskRepo.existsById(id)) throw new TaskNotFoundException("Task not found with id " + id);
		taskRepo.deleteById(id);
	}
	
	public List<TaskResponseDTO> findByStatus(Status status) {
		return taskRepo.findByStatus(status).stream()
				.map(TaskMapper::mapToTaskDto)
				.toList();
	}
	
	public List<TaskResponseDTO> findByPriority(Priority priority) {
		return taskRepo.findByPriority(priority).stream()
				.map(TaskMapper::mapToTaskDto)
				.toList();
	}
	
	public int taskCountByStatus(Status status) {
		return findByStatus(status).size();
	}
	
	public int taskCountByPriority(Priority priority) {
		return findByPriority(priority).size();
	}

}
