package com.luiz.guilherme.taskmanager.task_manager.service;

import com.luiz.guilherme.taskmanager.task_manager.model.Task;
import com.luiz.guilherme.taskmanager.task_manager.model.User;
import com.luiz.guilherme.taskmanager.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAllbyUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task findByIdAndUser(Long id, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado: Você não é o dono desta tarefa!");
        }

        return task;
    }

    public void delete(Long id, User user) {
        Task task = findByIdAndUser(id, user);
        taskRepository.delete(task);
    }
}
