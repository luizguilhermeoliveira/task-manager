package com.luiz.guilherme.taskmanager.task_manager.service;

import com.luiz.guilherme.taskmanager.task_manager.model.User;
import com.luiz.guilherme.taskmanager.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Este nome de usuário já esta em uso!");
        }

        return userRepository.save(user);
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário com username " + username + " não encontrado!"));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado!"));
    }
}
