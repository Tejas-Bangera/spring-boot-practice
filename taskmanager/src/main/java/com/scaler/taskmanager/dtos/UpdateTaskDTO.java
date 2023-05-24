package com.scaler.taskmanager.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
  String description;
  Boolean completed;
  Date dueDate;
}
