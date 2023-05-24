package com.scaler.taskmanager.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateTaskDTO {
  private String title;
  private String description;
  private Boolean completed;
  private Date dueDate;
}
