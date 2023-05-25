package com.scaler.blogapi.users;

import com.scaler.blogapi.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "users")
public class UserEntity extends BaseEntity {

  @Column(unique = true, nullable = false, length = 50)
  String username;
  String password; // TODO: Hash this  
  String bio;
  String image;
}
