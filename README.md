# DevOps-Assigment

## Installation

Use [Maven](https://maven.apache.org/) to build Java Redis Client.

```bash
mvn -f .\java\infobip-redis-service\pom.xml package -DskipTests=true
```

Build Docker images using [docker-compose](https://docs.docker.com/compose/).

```bash
docker-compose up
```

## Usage

Deploy stack using [docker-compose](https://docs.docker.com/compose/)

```bash
docker-compose up
```

## Architecture

Deployed stack consits of 3 Redis instances, 2 Sentinels, 3 Redis exportes, Prometheus, Grafana and Java Redis client.
