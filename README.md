
# SpringBoot Webflux Sales Ingestion and Metrics with Grafana and Prometheus

# Spring Boot WebFlux Sales Ingestion & Observability Demo

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)
![WebFlux](https://img.shields.io/badge/WebFlux-Reactive-6DB33F?style=for-the-badge)
![Prometheus](https://img.shields.io/badge/Prometheus-Metrics-E6522C?style=for-the-badge\&logo=prometheus\&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-Dashboards-F46800?style=for-the-badge\&logo=grafana\&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge\&logo=docker\&logoColor=white)

This project is a **reactive sales ingestion system** built with **Spring Boot WebFlux**, focused on demonstrating **real-time observability** using **Micrometer, Prometheus, and Grafana**.

It simulates a high-throughput ingestion pipeline for sales events and exposes **business metrics (not just system metrics)**, all provisioned **as code** and runnable locally or in GitHub Codespaces.


## Main Features

* Reactive, non-blocking ingestion of sales events using **Spring WebFlux**
* Business metrics exposed via **Micrometer**:
    * Total sales events
    * Items sold
    * Revenue accumulated
    * Ingestion latency
* **Prometheus** scraping via `/actuator/prometheus`
* **Grafana dashboards fully defined in code** (no UI setup required)
* Dataset replay / synthetic replay to simulate load
* Fully containerized stack using **Docker & Docker Compose**
* Ready-to-run in **GitHub Codespaces** via devcontainer

## How to clone and run locally

### Requirements

* Docker + Docker Compose
  (or GitHub Codespaces — no local setup needed)

### Clone the repository

```bash
git clone https://github.com/aghersidev/springboot-webflux.git
cd springboot-webflux
```

### Start the full stack

```bash
docker compose -f docker/docker-compose.yml up --build
```

This will start:

* Spring Boot API → [http://localhost:8080](http://localhost:8080)
* Prometheus → [http://localhost:9090](http://localhost:9090)
* Grafana → [http://localhost:3000](http://localhost:3000) (user: `admin`, password: `admin`)

## Sending sample sales events

Example request:

```bash
curl -X POST http://localhost:8080/sales \
  -H "Content-Type: application/json" \
  -d '{
    "invoiceNo": "INV-1",
    "stockCode": "A1",
    "quantity": 2,
    "unitPrice": 10.5,
    "customerId": "CUST-1",
    "country": "PE",
    "timestamp": "2026-01-01T00:00:00Z"
  }'
```
Metrics will immediately be reflected in Grafana.


## Grafana Dashboards

Dashboards are provisioned automatically at startup:

* No manual UI configuration
* Stored in version control
* Reproducible in any environment

Metrics visualized include:

* Sales per second
* Total revenue
* Items sold over time
* Ingestion latency (p95 / p99)


## Project structure (high level)

```
.
├── docker/                 # Dockerfile & docker-compose
├── grafana/                # Dashboards & provisioning
├── prometheus/             # Prometheus config
├── replay/                 # Dataset / synthetic replay scripts
├── src/
│   ├── main/java/...       # WebFlux API, services, metrics
│   └── main/resources/
└── .devcontainer/          # GitHub Codespaces setup
```


## Notes

* This project intentionally **does not implement OLTP persistence** in the base branch.
* The focus is on **event ingestion, reactivity, and observability**, not CRUD.
* A future branch can extend this with databases, customers, and transactional models.

---

## License

This project is licensed under the MIT License.

