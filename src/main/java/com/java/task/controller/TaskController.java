package com.java.task.controller;

import com.java.task.entity.Task;
import com.java.task.entity.User;
import com.java.task.service.TaskService;
import com.java.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public List<Task> getMyTasks(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return taskService.getTasksByUser(user.getId());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return taskService.createTask(task, user);
    }
}