package com.example.demoTasks.controller;

import com.example.demoTasks.dto.TaskRequest;
import com.example.demoTasks.dto.UpdateTaskRequest;
import com.example.demoTasks.entity.Task;
import com.example.demoTasks.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(value = "task")
    public ResponseEntity <Object> getTask(@RequestBody Task request){
        return taskService.getTaskByName(request);
    }

    @PostMapping(value = "task")
    public ResponseEntity<Object> postTask(@RequestBody TaskRequest request){
        return taskService.postTask(request);
    }

    @PutMapping(value = "task")
    public ResponseEntity<Object> putTask(@RequestBody UpdateTaskRequest request){
        return taskService.updateTask(request);
    }

    @DeleteMapping(path = "task/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable("taskId") Integer id){
        return taskService.deleteTask(id);
    }
}
