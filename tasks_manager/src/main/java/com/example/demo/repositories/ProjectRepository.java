package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
