package com.aghersidev.webfluxgrafana.service;

import com.aghersidev.webfluxgrafana.api.SalesRequest;
import com.aghersidev.webfluxgrafana.metrics.SalesMetrics;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SalesIngestionService {

    private final SalesMetrics metrics;

    public SalesIngestionService(SalesMetrics metrics) {
        this.metrics = metrics;
    }

    public Mono<Void> ingest(SalesRequest sale) {
        return Mono.fromRunnable(() -> {
            metrics.incrementSales();
            metrics.addItems(sale.quantity());
            metrics.addRevenue(sale.revenue());
        }).then();
    }
}
