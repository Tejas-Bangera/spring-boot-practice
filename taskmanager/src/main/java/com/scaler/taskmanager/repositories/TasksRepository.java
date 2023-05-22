package com.scaler.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.taskmanager.entities.TaskEntity;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer>{
  
}
