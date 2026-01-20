package com.aghersidev.webfluxgrafana;

import com.aghersidev.webfluxgrafana.api.SalesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxGrafanaTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void ingestSale_success() {
		var req = new SalesRequest(
				"INV-1",
				"SKU-1",
				2,
				15.5,
				"CUST-1",
				"PE",
				Instant.parse("2026-01-01T00:00:00Z")
		);

		webTestClient.post()
				.uri("/sales")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(req)
				.exchange()
				.expectStatus().is2xxSuccessful();
	}

	@Test
	void prometheusEndpoint_exposed() {
		webTestClient.get()
				.uri("/actuator/prometheus")
				.exchange()
				.expectStatus().isOk();
	}
}
