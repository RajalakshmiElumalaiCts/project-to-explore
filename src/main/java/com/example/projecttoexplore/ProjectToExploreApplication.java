package com.example.projecttoexplore;

import com.example.projecttoexplore.client.GreetingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProjectToExploreApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(ProjectToExploreApplication.class, args);
		String greetingText = context.getBean(GreetingClient.class).getGreeting().block();
		System.out.println("Greeting Received : "+ greetingText);
		CompletableFuture<String> future1
				= CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2
				= CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3
				= CompletableFuture.supplyAsync(() -> "World");

		CompletableFuture<Void> combinedFuture
				= CompletableFuture.allOf(future1, future2, future3);



        //try {
            System.out.println("combinedFuture : "+ combinedFuture.get());
        /*} catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/
    }

}
