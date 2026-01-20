package com.aghersidev.webfluxgrafana.api;

import java.time.Instant;

public record SalesRequest(
        String invoiceNo,
        String stockCode,
        int quantity,
        double unitPrice,
        String customerId,
        String country,
        Instant timestamp
) {
    public double revenue() {
        return quantity * unitPrice;
    }
}
