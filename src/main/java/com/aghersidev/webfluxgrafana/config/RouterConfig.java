package com.aghersidev.webfluxgrafana.config;

import com.aghersidev.webfluxgrafana.api.SalesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> salesRoutes(SalesHandler handler) {
        return route(POST("/sales"), handler::ingest);
    }
}
