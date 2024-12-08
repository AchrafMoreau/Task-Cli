package com.example.Todo_CLI.cli;

import com.example.Todo_CLI.entity.Task;

import java.util.List;

public class ListPrinter {
    private static final String border = "+-------+---------------------------+------------------------------------------+--------------+";
    private static final String header = "| ID    | Task                      | Description                              | Status       |";
    private static final String format = "| %-5s | %-25s | %-40s | %-12s |%n";

    public static void printTaskList(List<Task> tasks)
    {
        System.out.println(border);
        System.out.println(header);
        System.out.println(border);

        tasks.forEach(task -> System.out.printf(
                format,
                task.getId(),
                task.getTask(),
                task.getDesc(),
                task.getStatus().name()
        ));

        System.out.println(border);
    }
}
