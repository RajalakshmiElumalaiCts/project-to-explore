package com.example.projecttoexplore.config;

import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CustomWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String correlationId = null;
        if(! exchange.getRequest().getHeaders().containsKey("correlation-id")){
            correlationId = UUID.randomUUID().toString();
        } else
            correlationId =exchange.getRequest().getHeaders().get("correlation-id").get(0);

        MDC.put("correlation-id", correlationId);
        return chain.filter(exchange);
    }
}
