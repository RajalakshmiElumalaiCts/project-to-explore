package com.example.projecttoexplore.exercise;

public class CompletableFutureExercise {
    public Double getRandomNum() {
        System.out.println(Thread.currentThread().getName());
        return Math.random();
    }
}
