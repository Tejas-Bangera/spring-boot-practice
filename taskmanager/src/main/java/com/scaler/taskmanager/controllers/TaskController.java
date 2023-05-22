package com.scaler.taskmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.services.TaskService;

@RestController
public class TaskController {
  
  @Autowired TaskService taskService;

  @PostMapping("/tasks")
  public void createTask(@RequestBody TaskEntity task) {
    taskService.createTask(task);
  }

  @GetMapping("/tasks")
  public List<TaskEntity> getAllTasks() {
    return taskService.getAllTasks();
  }
}
