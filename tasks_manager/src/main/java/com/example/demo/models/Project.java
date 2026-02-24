package com.example.demo.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 30, message = "Name filed cannot be shorter than 5 characters and longer than 30")
	@NotBlank(message = "Name field value cannot be blank, empty or null")
	private String name;
	
	@Size(min = 10, max = 250, message = "Description filed cannot be shorter than 10 characters and longer than 250")
	@NotBlank(message = "Description field value cannot be blank, empty or null")
	private String description;
	
	@NotNull
	private LocalDate creationDate;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, 
			orphanRemoval = true)
	List<Task> tasks;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_project", 
    joinColumns = @JoinColumn(name = "project_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> users;
	
	public void addTask(Task t) {
		tasks.add(t);
	}
	
	public void removeTask(Task t) {
		tasks.remove(t);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}

