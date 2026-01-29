package com.luiz.guilherme.taskmanager.task_manager.controller;

import com.luiz.guilherme.taskmanager.task_manager.model.User;
import com.luiz.guilherme.taskmanager.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.create(user);

        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delte(@PathVariable Long id, @RequestParam String myUsername) {
        User userLogado = userService.findByUsername(myUsername);
        userService.deleteSelf(id, userLogado);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUserName(@PathVariable String username) {
        User requestedUser = userService.findByUsername(username);

        return ResponseEntity.ok(requestedUser);
    }
}
