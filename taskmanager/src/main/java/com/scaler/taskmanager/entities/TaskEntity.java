package com.scaler.taskmanager.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tasks")
@Getter
@Setter
public class TaskEntity extends BaseEntity {
  
  @Column(name = "title", nullable = false, length = 150)
  String title;

  @Column(name = "description", nullable = true, length = 500)
  String description;

  @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
  Boolean completed;

  @Column(name = "due_date", nullable = true)
  Date dueDate;
}
