package com.example.Todo_CLI;

import com.example.Todo_CLI.cli.CliManger;
import com.example.Todo_CLI.repository.CrudTaskRepository;
import com.example.Todo_CLI.repository.CrudTaskRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TodoCliApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(TodoCliApplication.class, args);
		CliManger cliManger = context.getBean(CliManger.class);
		cliManger.run();

	}

}
