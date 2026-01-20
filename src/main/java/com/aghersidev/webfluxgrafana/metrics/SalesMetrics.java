package com.aghersidev.webfluxgrafana.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class SalesMetrics {

    private final Counter salesEvents;
    private final Counter itemsSold;
    private final Counter revenue;
    private final Timer processingTimer;

    public SalesMetrics(MeterRegistry registry) {
        this.salesEvents = Counter.builder("sales_events_total").register(registry);
        this.itemsSold = Counter.builder("sales_items_total").register(registry);
        this.revenue = Counter.builder("sales_revenue_total").register(registry);
        this.processingTimer = Timer.builder("sales_processing_seconds").register(registry);
    }

    public void incrementSales() {
        salesEvents.increment();
    }

    public void addItems(int quantity) {
        itemsSold.increment(quantity);
    }

    public void addRevenue(double amount) {
        revenue.increment(amount);
    }

    public Timer.Sample startTimer() {
        return Timer.start();
    }

    public void stopTimer(Timer.Sample sample) {
        sample.stop(processingTimer);
    }
}
