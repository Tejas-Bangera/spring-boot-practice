package com.scaler.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.repositories.TasksRepository;

@Service
public class TaskService {
  @Autowired TasksRepository tasksRepository;

  public List<TaskEntity> getAllTasks() {
    return tasksRepository.findAll();
  }

  public void createTask(TaskEntity task) {
    tasksRepository.save(task);
  }
}
