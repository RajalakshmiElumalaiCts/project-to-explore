package com.example.projecttoexplore.router;

import com.example.projecttoexplore.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/greet").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::greet)
                .andRoute(GET("/greet/{name}").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::greetForSpecificId)
                .andRoute(POST("/greet").and(accept(org.springframework.http.MediaType.APPLICATION_JSON)), greetingHandler::createGreet)
                .andRoute(PATCH("/greet/{name}").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::updateGreet)
                .andRoute(DELETE("/greet/{name}").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::deleteGreet);
    }

    @Bean
    public RouterFunction<ServerResponse> routeRaji() {
        return RouterFunctions.route(GET("/raji").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.badRequest().bodyValue("No endpoint available !!"));
    }

    /*@Bean
    public RouterFunction<ServerResponse> routeWithQueryParam(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/greet").and(accept(MediaType.APPLICATION_JSON),
                        RequestPredicates.all()
                                .and(queryParam("name")), greetingHandler::greet);
    }*/
}
