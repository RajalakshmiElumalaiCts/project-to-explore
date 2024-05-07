package com.example.projecttoexplore.handler;

import com.example.projecttoexplore.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> greet(ServerRequest request) {
        var response = ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("unknown","Hello Developer!!")));
        return response;
    }

    public Mono<ServerResponse> greetall(ServerRequest request) {
        var response = ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(
                        Arrays.asList(
                                new Greeting("Person 1", "message 1"),
                                new Greeting("Person 2", "message 2"),
                                new Greeting("Person 3", "message 3"),
                                new Greeting("unknown","Hello Developer!!")
                        )), Greeting.class);
        return response;
    }

    public Mono<ServerResponse> greetForSpecificId(ServerRequest request) {
        var response =  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        new Greeting(
                                request.pathVariable("name"),
                               String.join(" ", "Hello", request.pathVariable("name"),
                                      request.queryParam("message").orElse(", doing Great Job!!"))
                                       )));
        return response;
    }

    public Mono<ServerResponse> createGreet(ServerRequest request) {

        /*return request.bodyToMono(Greeting.class)
                .flatMap(greeting -> ServerResponse.noContent().build());*/
        return request.bodyToMono(Greeting.class)
                        .flatMap(greeting -> ServerResponse.created(URI.create("/greet/"+greeting.getName())).build());
    }

    public Mono<ServerResponse> updateGreet(ServerRequest request) {
        return ServerResponse.ok()
                .bodyValue(String.join(" ","Greeting updated for ", request.pathVariable("name")));
    }

    public Mono<ServerResponse> deleteGreet(ServerRequest request) {
        return ServerResponse.ok()
                .bodyValue(String.join(" ","Greeting deleted for ", request.pathVariable("name")));
    }

}
