package com.aghersidev.webfluxgrafana.api;

import com.aghersidev.webfluxgrafana.service.SalesIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SalesHandler {

    private final SalesIngestionService service;
    private static final Logger log = LoggerFactory.getLogger(SalesHandler.class);

    public SalesHandler(SalesIngestionService service) {
        this.service = service;
    }

    public Mono<ServerResponse> ingest(ServerRequest request) {
        return request.bodyToMono(SalesRequest.class)
                .doOnNext(req -> log.info("Ingesting sale {}", req.invoiceNo()))
                .flatMap(service::ingest)
                .then(ServerResponse.accepted().build());
    }
}
