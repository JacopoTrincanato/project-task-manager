package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

