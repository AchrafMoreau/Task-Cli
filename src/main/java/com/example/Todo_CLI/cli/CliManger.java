package com.example.Todo_CLI.cli;

import com.example.Todo_CLI.entity.Status;
import com.example.Todo_CLI.entity.Task;
import com.example.Todo_CLI.exception.NoTaskWasFoundException;
import com.example.Todo_CLI.repository.CrudTaskRepository;
import com.example.Todo_CLI.repository.CrudTaskRepositoryImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.*;

@Component
public class CliManger {

    private final Scanner scanner;
    private final CrudTaskRepository taskRepo;

    public CliManger(CrudTaskRepository taskRepo){
        this.scanner = new Scanner(in);
        this.taskRepo = taskRepo;

    }


    public void run(){
        String command;

        while (!(command = scanner.next().toLowerCase()).equals("exit")){
            switch (command){
                case "add": add();
                    break;
                case "edit": edit();
                    break;
                case "show": show();
                    break;
                case "delete": delete();
                    break;
                case "list" : list();
                    break;
                default:
                    out.println("Unknown Command Ya Weld Nass");
            }
        }

        exit();

    }

    public void exit(){
        out.println("Idhab Ila 7ali Sabilik ¯\\\\_(ツ)_/¯");
        scanner.close();
    }

    public void add()
    {
        String task = scanner.nextLine().trim();
        String[] info = task.split(" ");
        try{
            Status st = Status.stringToCommand(info[3]);
            int id = Integer.parseInt(info[0]);
            Task newTask = new Task(id, info[1], info[2], st);
            taskRepo.store(newTask);
            out.println("Task Was Created Successfully");
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void edit()
    {
        String task = scanner.nextLine().trim();
        String[] info = task.split(" ");
        try{
            Status st = Status.stringToCommand(info[3]);
            int id = Integer.parseInt(info[0]);
            Task newTask = new Task(id, info[1], info[2], st);
            taskRepo.update(newTask.getId(), newTask);
            out.println("Task Was Updated Successfully");
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void show()
    {
        String task = scanner.nextLine().trim();
        String[] info = task.split(" ");
        int id = Integer.parseInt(info[0]);

        try{
            Task findedTask = taskRepo.findById(id)
                    .orElseThrow(() -> new NoTaskWasFoundException("Task Not Found"));
            out.println("Task : " + findedTask.getTask() + "\n" +
                    "Description : " + findedTask.getDesc() + "\n" +
                    "Status : " + findedTask.getStatus());
        }catch (IllegalArgumentException e){
            out.println(e.fillInStackTrace());
        }

    }

    public void delete()
    {
        String command = scanner.nextLine().trim();
        String id = command.split(" ")[0];
        try{
            taskRepo.delete(Integer.parseInt(id));
            out.println("Task Was Deleted Successfully");
        }catch(NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void list()
    {
        try{
            List<Task> tasks = taskRepo.list();

            tasks.forEach(task ->
                    out.println("Task : " + task.getTask() + "\t" +
                            "Description : " + task.getDesc() + "\t" +
                            "Status : " + task.getStatus().name())
            );
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }



}
