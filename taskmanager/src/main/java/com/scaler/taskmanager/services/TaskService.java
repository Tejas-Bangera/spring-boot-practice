package com.scaler.taskmanager.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.repositories.TasksRepository;

@Service
public class TaskService {
  @Autowired TasksRepository tasksRepository;

  public static class TaskNotFoundException extends IllegalStateException {
    public TaskNotFoundException() {
      super("Task not found!");
    }
    public TaskNotFoundException(Integer id) {
      super("Task with id " + id + " not found!");
    }
  }

  public List<TaskEntity> getAllTasks() {
    return tasksRepository.findAll();
  }

  public TaskEntity createTask(String title, String description, Boolean completed, Date dueDate) {
    TaskEntity task = new TaskEntity();
    task.setTitle(title);
    task.setDescription(description);
    task.setCompleted(completed);
    task.setDueDate(dueDate);
    return tasksRepository.save(task);
  }

  public TaskEntity getTaskById(Integer id) {
    Optional<TaskEntity> task = tasksRepository.findById(id);

    if(task.isEmpty()) throw new TaskNotFoundException(id);

    return task.orElseThrow();
  }

  public List<TaskEntity> getTaskByTitle(String title) {
    return tasksRepository.findByTitle(title);
  }

  public void deleteTask(Integer id) {
    tasksRepository.deleteById(id);
  }

  public TaskEntity updateTask(Integer id, String description, Boolean completed, Date dueDate) {
    TaskEntity task = getTaskById(id);

    if(task == null) return null;

    if(description != null) task.setDescription(description);
    if(completed != null) task.setCompleted(completed);
    if(dueDate != null) task.setDueDate(dueDate);

    return tasksRepository.save(task);
  }

  public List<TaskEntity> getAllTasksByCompleted(Boolean completed) {
    return tasksRepository.findByCompleted(completed);
  }
}
