package com.scaler.taskmanager.services;

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
    public TaskNotFoundException(Integer id) {
      super("Task with id " + id + " not found!");
    }
  }

  public List<TaskEntity> getAllTasks() {
    return tasksRepository.findAll();
  }

  public TaskEntity createTask(TaskEntity task) {
    return tasksRepository.save(task);
  }

  public TaskEntity getTask(Integer id) {
    Optional<TaskEntity> task = tasksRepository.findById(id);

    if(task.isEmpty()) throw new TaskNotFoundException(id);

    return task.orElseThrow();
  }

  public void deleteTask(Integer id) {
    tasksRepository.deleteById(id);
  }

  public void updateTask(Integer id, TaskEntity updateTask) {
    // TBU
  }
}
