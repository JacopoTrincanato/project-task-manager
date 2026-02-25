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

import com.example.demo.dto.create.ProjectCreateDTO;
import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.exceptions_errors.ProjectNotFoundException;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utilities.BaseService;

import jakarta.transaction.Transactional;

@Service
public class ProjectService implements BaseService<ProjectResponseDTO, ProjectCreateDTO, Long> {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public ProjectResponseDTO create(ProjectCreateDTO dto) {

		List<Task> tasks = dto.taskIds() != null
		        ? taskRepo.findAllById(dto.taskIds())
		        : new ArrayList<>();

		Set<User> users = dto.userIds() != null
		        ? new HashSet<>(userRepo.findAllById(dto.userIds()))
		        : new HashSet<>();

	    Project project = ProjectMapper.mapToProject(dto, tasks, users);

	    Project saved = projectRepo.save(project);

	    return ProjectMapper.mapToProjectResponseDto(saved);
	}
	
	@Override
	@Transactional
	public ProjectResponseDTO update(ProjectCreateDTO dto, Long id) {
		// TODO Auto-generated method stub
		Project project = projectRepo.findById(id).orElseThrow(
				() -> new ProjectNotFoundException("Project not Found with id: " + id)
				);
		
		project.setName(dto.name());
		project.setDescription(dto.description());
		project.setCreationDate(dto.creationDate());
		
		List<Task> tasks = dto.taskIds() != null
		        ? taskRepo.findAllById(dto.taskIds())
		        : new ArrayList<>();

		Set<User> users = dto.userIds() != null
		        ? new HashSet<>(userRepo.findAllById(dto.userIds()))
		        : new HashSet<>();
		
		project.setTasks(tasks);
		project.setUsers(users);
		
		Project saved = projectRepo.save(project);
		
		return ProjectMapper.mapToProjectResponseDto(saved);
	}

	@Override
	public ProjectResponseDTO findById(Long id) {
		// TODO Auto-generated method stub
		Project project = projectRepo.findById(id).orElseThrow(
				() -> new ProjectNotFoundException("Project not Found with id: " + id)
				);
		return ProjectMapper.mapToProjectResponseDto(project);
	}

	@Override
	public ContentPagination<ProjectResponseDTO> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Project> projects = projectRepo.findAll(pageable);
		List<Project> listOfProjects = projects.getContent();
		List<ProjectResponseDTO> content = listOfProjects.stream()
				.map(ProjectMapper::mapToProjectResponseDto)
				.toList();
		
		ContentPagination<ProjectResponseDTO> projectResponse = new ContentPagination<>();
		projectResponse.setContent(content);
		projectResponse.setPageNo(projects.getNumber());
		projectResponse.setPageSize(projects.getSize());
		projectResponse.setTotalElements(projects.getTotalElements());
		projectResponse.setTotalPages(projects.getTotalPages());
		projectResponse.setLast(projects.isLast());
		
		return projectResponse;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		projectRepo.deleteById(id);
	}

}
