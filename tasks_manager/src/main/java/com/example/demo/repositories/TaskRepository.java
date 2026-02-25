package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import com.example.demo.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByStatus(Status status);
	List<Task> findByPriority(Priority priority);
}

