package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.create.ProjectCreateDTO;
import com.example.demo.dto.response.ProjectResponseDTO;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.models.Project;
import com.example.demo.pagination.ContentPagination;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.utilities.BaseService;

import jakarta.transaction.Transactional;

public class ProjectService implements BaseService<ProjectResponseDTO, ProjectCreateDTO, Long> {
	
	@Autowired
	private ProjectRepository projectRepo;

	@Override
	@Transactional
	public ProjectCreateDTO create(ProjectCreateDTO entity) {
		// TODO Auto-generated method stub
		
		return projectRepo.save(entity);
	}
	
	@Override
	@Transactional
	public Project update(Project entity, Long id) {
		// TODO Auto-generated method stub
		
		return projectRepo.save(entity);
	}

	@Override
	public ProjectResponseDTO findById(Long id) {
		// TODO Auto-generated method stub
		Project project = projectRepo.findById(id).orElse(null);
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
