package com.example.projecttoexplore.model;

import lombok.Data;

@Data
public class Greeting {
    private String message;
    private String name;

    //public Greeting() { }

    public Greeting(String name, String message) {
        this.message = message;
        this.name = name;
    }

    public Greeting(Greeting greeting) {
        this.name = greeting.getName();
        this.message = greeting.getMessage();
    }
}
