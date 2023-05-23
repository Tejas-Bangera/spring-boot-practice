package com.scaler.taskmanager.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.services.TaskService;

@RestController
public class TaskController {
  
  @Autowired TaskService taskService;

  @PostMapping("/tasks")
  public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
    var newTask = taskService.createTask(task);
    return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
  }

  @GetMapping("/tasks")
  public ResponseEntity<List<TaskEntity>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }
  
  @GetMapping("/tasks/{id}")
  public ResponseEntity<Optional<TaskEntity>> getTask(@PathVariable Integer id) {
    return ResponseEntity.ok(taskService.getTask(id));
  }

}
