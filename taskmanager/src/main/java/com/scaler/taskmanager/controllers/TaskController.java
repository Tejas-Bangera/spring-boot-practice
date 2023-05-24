package com.scaler.taskmanager.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.taskmanager.dtos.CreateTaskDTO;
import com.scaler.taskmanager.dtos.ErrorResponse;
import com.scaler.taskmanager.dtos.UpdateTaskDTO;
import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired TaskService taskService;

  /**
   * Creates a new Task
   * @param body
   * @return Response OK with created task
   */
  @PostMapping("")
  public ResponseEntity<TaskEntity> createTask(@RequestBody CreateTaskDTO body) {
    var newTask = taskService.createTask(body.getTitle(), body.getDescription(), body.getCompleted(), body.getDueDate());

    return ResponseEntity.ok(newTask);
  }

  /**
   * Fetch all Tasks
   * @return List of Tasks
   */
  @GetMapping("")
  public ResponseEntity<List<TaskEntity>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }
  
  // Fetch a task with the given id
  /**
   * Fetch a Task by id
   * @param id
   * @return Response OK with the fetched Task
   * @throws TaskService.TaskNotFoundException
   */
  @GetMapping("/{id}")
  public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id) {
    TaskEntity task = (taskService.getTaskById(id));

    if(task == null) throw new TaskService.TaskNotFoundException(id);

    return ResponseEntity.ok(task);
  }

  /**
   * Fetch a Task by title
   * @param title
   * @return Response OK with List of Tasks
   * @throws TaskService.TaskNotFoundException
   */
  @GetMapping(
    value = "",
    params = "title"
  )
  public ResponseEntity<List<TaskEntity>> getTaskByTitle(@RequestParam("title") String title) {
    List<TaskEntity> tasks = taskService.getTaskByTitle(title);

    if(tasks.isEmpty()) throw new TaskService.TaskNotFoundException();

    return ResponseEntity.ok(tasks);
  }

  /**
   * Fetch Tasks by completed
   * @param completed
   * @return List of Tasks
   */
  @GetMapping(
    value = "",
    params = "completed"
  )
  public ResponseEntity<List<TaskEntity>> getAllTasksByCompleted(@RequestParam("completed") Boolean completed) {
    List<TaskEntity> tasks = taskService.getAllTasksByCompleted(completed);

    return ResponseEntity.ok(tasks);
  }
  
  /**
   * Delete a Task by id
   * @param id
   */
  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Integer id) {
    taskService.deleteTask(id);
  }

  /**
   * Update a Task by id
   * @param id
   * @param updateTaskDTO
   * @return Response OK with updated Task
   * @throws TaskService.TaskNotFoundException
   */
  @PatchMapping("/{id}")
  public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) {
    var task = taskService.updateTask(id, updateTaskDTO.getDescription(), updateTaskDTO.getCompleted(), updateTaskDTO.getDueDate());

    if(task == null) throw new TaskService.TaskNotFoundException(id);

    return ResponseEntity.ok(task);
  }

  /**
   * Method to handle exceptions
   * @param e
   * @return ErrorResponse with appropriate HttpStatus code
   */
  @ExceptionHandler(TaskService.TaskNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleErrors(TaskService.TaskNotFoundException e) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
  }
}
