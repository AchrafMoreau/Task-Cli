package com.example.Todo_CLI.entity;

import jakarta.persistence.*;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String task;
    @Column(name="description")
    private String desc;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(int id, String task, String desc, Status status){
        this.id = id;
        this.task = task;
        this.desc = desc;
        this.status = status;
    }
    public Task() {};


    public int getId() {
        return this.id;
    }

    public Status getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
