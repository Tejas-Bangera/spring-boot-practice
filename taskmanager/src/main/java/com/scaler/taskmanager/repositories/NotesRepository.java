package com.scaler.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.taskmanager.entities.NoteEntity;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer>{
  
}
