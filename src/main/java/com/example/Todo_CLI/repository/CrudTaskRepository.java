package com.example.Todo_CLI.repository;

import com.example.Todo_CLI.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CrudTaskRepository {

    public void store(Task task);
    public void update(int id, Task task);
    public Optional<Task> findById(int id);
    public List<Task> list();
    public void delete(int id);
}
