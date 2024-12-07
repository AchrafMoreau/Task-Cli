package com.example.Todo_CLI.exception;

public class NoTaskWasFoundException extends RuntimeException{
    public NoTaskWasFoundException(String message){
        super(message);
    }
}
