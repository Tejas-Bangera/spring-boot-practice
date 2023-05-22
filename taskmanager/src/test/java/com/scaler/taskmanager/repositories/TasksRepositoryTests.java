package com.scaler.taskmanager.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.scaler.taskmanager.entities.TaskEntity;

@DataJpaTest
public class TasksRepositoryTests {
  @Autowired TasksRepository tasksRepository;

  @Test
  public void testCreateTask() {
    TaskEntity task = new TaskEntity();
    task.setTitle("Test Task");
    task.setDescription("Test Description");
    task.setCompleted(false);
    var savedTask = tasksRepository.save(task);
 
    assertNotNull(savedTask);
  }

  @Test
  public void testReadTask() {
    TaskEntity task1 = new TaskEntity();
    task1.setTitle("Test Task1");
    task1.setDescription("Task1 Description");
    task1.setCompleted(false);
    
    TaskEntity task2 = new TaskEntity();
    task2.setTitle("Test Task2");
    task2.setDescription("Task2 Description");
    task2.setCompleted(false);

    tasksRepository.save(task1);
    tasksRepository.save(task2);

    var tasks = tasksRepository.findAll();
    assertNotNull(tasks);
    assertEquals(2, tasks.size());
  }
}
