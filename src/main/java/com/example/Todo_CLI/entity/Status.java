package com.example.Todo_CLI.entity;

public enum Status {
    COMPLETE,
    IN_PROGRESS,
    NEW;

    public static Status stringToCommand(String status){
        for(Status st : Status.values()){
            if(st.name().equalsIgnoreCase(status)){
                return st;
            }
        }
        throw new IllegalArgumentException("Unknown STATUS");
    }

}
