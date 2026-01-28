package com.luiz.guilherme.taskmanager.task_manager.repository;

import com.luiz.guilherme.taskmanager.task_manager.model.Task;
import com.luiz.guilherme.taskmanager.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
