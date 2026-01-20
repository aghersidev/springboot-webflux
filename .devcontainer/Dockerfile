FROM maven:3.9.6-eclipse-temurin-21

RUN apt-get update && apt-get install -y \
    docker.io \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace
