package com.scaler.taskmanager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.taskmanager.dtos.ErrorResponse;
import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.services.TaskService;

@RestController
public class TaskController {
  
  @Autowired TaskService taskService;

  // Create a task
  @PostMapping("/tasks")
  public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
    var newTask = taskService.createTask(task);
    return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
  }

  // Fetch all tasks
  @GetMapping("/tasks")
  public ResponseEntity<List<TaskEntity>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }
  
  // Fetch a task with the given id
  @GetMapping("/tasks/{id}")
  public ResponseEntity<TaskEntity> getTask(@PathVariable Integer id) {
    return ResponseEntity.ok(taskService.getTask(id));
  }

  // Delete a task with given id
  @DeleteMapping("/tasks/{id}")
  public void deleteTask(@PathVariable Integer id) {
    taskService.deleteTask(id);
  }

  // Update a task with given id
  @PatchMapping("/tasks/{id}")
  public void updateTask(@PathVariable Integer id, @RequestBody TaskEntity updateTask) {
    taskService.updateTask(id, updateTask);
  }

  // Handle errors
  @ExceptionHandler(TaskService.TaskNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleErrors(TaskService.TaskNotFoundException e) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
