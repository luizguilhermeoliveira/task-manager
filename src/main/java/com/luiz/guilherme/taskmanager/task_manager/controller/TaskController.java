package com.luiz.guilherme.taskmanager.task_manager.controller;

import com.luiz.guilherme.taskmanager.task_manager.model.Task;
import com.luiz.guilherme.taskmanager.task_manager.model.User;
import com.luiz.guilherme.taskmanager.task_manager.service.TaskService;
import com.luiz.guilherme.taskmanager.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Task>> getAllByUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.findAllbyUser(user);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/user/{username}")
    public ResponseEntity<Task> create(@PathVariable String username, @RequestBody Task task) {
        User user = userService.findByUsername(username);
        task.setUser(user);

        Task newTask = taskService.create(task);
        return ResponseEntity.ok(newTask);
    }

    @DeleteMapping("{id}/user/{username}")
    public ResponseEntity<Void> delte(@PathVariable Long id, @PathVariable String username) {
        User user = userService.findByUsername(username);
        taskService.delete(id, user);

        return ResponseEntity.noContent().build();
    }
}
