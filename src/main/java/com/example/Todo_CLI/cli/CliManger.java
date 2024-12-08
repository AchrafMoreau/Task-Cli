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
                case "add" -> add();
                case "edit" -> edit();
                case "show" -> show();
                case "delete" -> delete();
                case "list" -> list();
                case "mark-as" -> markAs();
                case "help" -> help();
                case "list-new" -> listNew();
                case "list-complete" -> listComplete();
                case "list-in-progress" -> listInProgress();
                default ->
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
            ListPrinter.printTaskList(tasks);

        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void listNew()
    {
        try{
            List<Task> tasks = taskRepo.listNew();
            ListPrinter.printTaskList(tasks);
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void listComplete()
    {
        try{
            List<Task> tasks = taskRepo.listComplete();
            ListPrinter.printTaskList(tasks);
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void listInProgress()
    {
        try{
            List<Task> tasks = taskRepo.listInProgress();
            ListPrinter.printTaskList(tasks);
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void markAs()
    {
        String command = scanner.nextLine().trim();
        String[] info = command.split(" ");
        try{
            int id = Integer.parseInt(info[0]);
            Status mark = taskRepo.markAs(id, info[1]);
            out.printf("Task Was Mark As %s%n", mark.name());
        }catch (NoTaskWasFoundException e){
            out.println(e.getMessage());
        }
    }

    public void help()
    {
        String helperMessage = """
                    - add [id] [task] [description] [status] : Add a new task
                    - update [id] [task] [description] [status] : Update a task
                    - delete [id] : Delete a task
                    - mark-as [id] [status] : Mark a task as in Status you want
                    - list : List all tasks
                    - list-new : List all New tasks
                    - list-in-progress : List all In-Progress tasks
                    - list-complete : List all Completed tasks
                    - exit : Exit the program
                """;
        out.println(helperMessage);
    }


}
