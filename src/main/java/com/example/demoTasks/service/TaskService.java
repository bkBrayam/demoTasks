package com.example.demoTasks.service;

import com.example.demoTasks.dto.TaskRequest;
import com.example.demoTasks.dto.UpdateTaskRequest;
import com.example.demoTasks.entity.Task;
import com.example.demoTasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Object> getTaskByName(Task request){
        Task taskFound = taskRepository.findTaskByTitle(request.getTitle());

        if(taskFound != null) {

            Map<String, Object> succesfulResponse = new HashMap<>();
            succesfulResponse.put("mensaje","tarea encontrada con exito");
            succesfulResponse.put("data",taskFound);
            return ResponseEntity.ok(succesfulResponse);
        }else {
            Map<String, String> badResponse = new HashMap<>();
            badResponse.put("mensaje","tarea no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badResponse);
        }
    }

    public ResponseEntity<Object> postTask(TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.getCompleted())
                .materials(request.getMaterials())
                .build();

        taskRepository.save(task);
        return ResponseEntity.ok(task);
    }


    public ResponseEntity<Object> updateTask(UpdateTaskRequest request) {
        Optional<Task> taskFound = taskRepository.findById(request.getId());

        if(taskFound.isPresent()){
            Task taskToUpdate = Task.builder()
                .id(request.getId())
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .completed(request.getCompleted())
                    .materials(request.getMaterials())
                    .build();

            taskRepository.save(taskToUpdate);

            return ResponseEntity.ok(taskToUpdate);
        } else {
            Map<String, String> badResponse = new HashMap<>();
            badResponse.put("mensaje","producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badResponse);
        }
    }

    public ResponseEntity<Object> deleteTask(Integer id) {

        Optional<Task> taskFound = taskRepository.findById(id);

        if(taskFound.isPresent()){
            taskRepository.deleteById(id);
            Map<String, String> succesResponse = new HashMap<>();
            succesResponse.put("mensaje", "La tarea se ha eliminado con éxito");
            return ResponseEntity.ok(succesResponse);
        }else {
            Map<String, String> succesResponse = new HashMap<>();
            succesResponse.put("mensaje", "No se ha encontrado tarea");
            return ResponseEntity.ok(succesResponse);
        }
    }


}
