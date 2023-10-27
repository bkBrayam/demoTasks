package com.example.demoTasks.repository;

import com.example.demoTasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    ResponseEntity<Object> findTaskByTitle(String title);
}
