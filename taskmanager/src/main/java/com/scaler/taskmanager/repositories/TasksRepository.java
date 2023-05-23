package com.scaler.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scaler.taskmanager.entities.TaskEntity;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer>{

  // List<TaskEntity> findAllByCompleted(boolean completed);

  // @Query("SELECT t FROM tasks t WHERE t.completed = false AND t.dueDate < CURRENT_DATE")
  // List<TaskEntity> findAllOverdue();

  // List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}
