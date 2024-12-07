package com.example.Todo_CLI.repository;

import com.example.Todo_CLI.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

@Repository
public class CrudTaskRepositoryImpl implements CrudTaskRepository {


    @PersistenceContext
    private EntityManager taskManger;


    @Transactional
    @Override
    public void store(Task task) {
        String jpql = "INSERT INTO task (id, task, description, status) VALUE(?,?,?,?)";
        Query query = taskManger.createNativeQuery(jpql);
        query.setParameter(1, task.getId());
        query.setParameter(2, task.getTask());
        query.setParameter(3, task.getDesc());
        query.setParameter(4, task.getStatus().name());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void update(int id, Task task) {

        String jpql = "UPDATE Task t " +
                "SET t.task = :task, t.description = :description, t.status = :status " +
                "WHERE t.id = :id";
        try{
            Query query = taskManger.createNativeQuery(jpql);
            query.setParameter("task", task.getTask());
            query.setParameter("description", task.getDesc());
            query.setParameter("status", task.getStatus());
            query.setParameter("id", task.getId());
            query.executeUpdate();

        }catch (RuntimeException e){
            out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Task> findById(int id) {
        String jpql = "SELECT e FROM Task e WHERE e.id = :id";
        Task task = (Task) taskManger.createQuery(jpql, Task.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(task);
    }

    @Override
    public List<Task> list() {
        String jpql = "SELECT t FROM Task t";
        Query query = taskManger.createQuery(jpql);
        List<Task> tasks = query.getResultList();
        return tasks;
    }

    @Transactional
    @Override
    public void delete(int id) {
        String jpql = "DELETE FROM Task t WHERE t.id = :id";
        Query query = taskManger.createQuery(jpql);
        query.setParameter("id", id).executeUpdate();
    }
}
