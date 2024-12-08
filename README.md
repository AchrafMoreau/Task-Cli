# CLI Task Manager ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-green) ![Java](https://img.shields.io/badge/Java-12-blue) ![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)

A Command-Line Interface (CLI) task manager built with Spring Boot. This application allows you to manage tasks with basic functionality such as creating, updating, listing, and deleting tasks. Tasks are stored in a database and manipulated through simple CLI commands.

## Features 🚀

- **Create**: Add a new task with details like task name, description, and status.
- **Update**: Modify an existing task's details (e.g., task name, description, or status).
- **List**: View all tasks stored in the system.
- **Delete**: Remove tasks from the database.
- **Mark-as** : Mark any task with any status you wannat.

## Technologies Used 🛠️

- **Spring Boot**: Main framework used to build the application.
- **JPA (Java Persistence API)**: Used for interacting with the MySQL database and managing entities.
- **MySQL**: The relational database used for storing task information.
- **JPQL (Java Persistence Query Language)**: Custom repository methods use JPQL to query and manipulate task data.
- **CLI (Command-Line Interface)**: Interacts with the application to manage tasks.

## Prerequisites ⚙️

- **JDK 12+**: Make sure you have Java Development Kit 12 or higher installed.
- **MySQL 8.0+**: The application uses MySQL as the database.

## Setup 🏗️

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/cli-task-manager.git
cd Task-Cli
```

Sample solution for the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge.