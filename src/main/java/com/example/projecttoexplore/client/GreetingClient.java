package com.example.projecttoexplore.client;

import com.example.projecttoexplore.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class GreetingClient {

    private WebClient webClient;

    public GreetingClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getGreeting() {
        //Example of passing more than 1 path variable with query param
        webClient.post().uri("/{param1}/{param2}/go",
                uriBuilder -> uriBuilder
                        .queryParam("qparam1","value1")
                        .queryParam("qparam2","value2")
                        .build("pathParamVal1", "pathParamVal2"));

        return this.webClient.get()
                .uri("/greet")
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);

    }
}
