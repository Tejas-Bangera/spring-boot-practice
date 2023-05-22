package com.scaler.taskmanager.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
  @SequenceGenerator(name = "id_seq", initialValue = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt;
}
