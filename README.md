# DevOps-Assigment

Deployed stack consists of three Redis instances, two Redis Sentinels, three Redis exporters, Prometheus, Grafana and Java Redis client.

Java client communicates with Redis Sentinels to get the location of Redis Master instance. In case Redis Master goes down failover will happen and one of the other two instances will become the new master. 

To monitor the stack we use [Prometheus](https://prometheus.io/) server that scrapes metrics from Redis exporters. Visualization of the metrics is done using [Grafana](https://grafana.com/). Grafana instance comes with preconfigured datasource and [Redis Dashboard](https://grafana.com/grafana/dashboards/763).

### Component Overview

| Component          | Image                                    | Instances  |
| -------------------|:----------------------------------------:| ----------:|
| Redis              | `redis:5.0`                              |     3      |
| Redis Sentinel     | `redis:5.0`                              |     2      |
| Redis exporter     | `oliver006/redis_exporter:v1.5.2-alpine` |     3      |
| Java Redis client  | `openjdk:8-slim`                         |     1      |
| Prometheus         | `prom/prometheus:v2.16.0`                |     1      |
| Grafana            | `grafana/grafana:6.7.0`                  |     1      |

## Architecture Overview

![overview](./assets/../assets/DevOps&#32;Assigment-overview.png)

## Requirements

[Docker](https://docs.docker.com/install/), [Docker Compose](https://docs.docker.com/compose/), [Java JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) and [Apache Maven](https://maven.apache.org/) are required to run this stack.

## Installation

Use [Maven](https://maven.apache.org/) to build Java Redis Client.

```bash
mvn -f .\docker\java\infobip-redis-service\pom.xml package
```

Build Docker images using [docker-compose](https://docs.docker.com/compose/).

```bash
docker-compose -f .\docker\docker-compose.yml build
```

Pull all required images using [docker-compose](https://docs.docker.com/compose/).

```bash
docker-compose -f .\docker\docker-compose.yml pull
```

## Test

To test Java Redis Client run integration tests using [Maven](https://maven.apache.org/).

```bash
mvn -f .\docker\java\infobip-redis-service\pom.xml test
```

## Deployment

Deploy stack using [docker-compose](https://docs.docker.com/compose/).

```bash
docker-compose -f .\docker\docker-compose.yml up
```

To run docker containers in detached mode:

```bash
docker-compose -f .\docker\docker-compose.yml up -d
```

## Usage

Check Grafana for Redis metrics using this [link](http://localhost:3000).

To access Grafana use username `admin` and password `secret`. Password can be configured in grafana service section in docker-compose.yml.
Redis dashboard can be found in `General` folder.

For interaction with Redis Client go to this [Swagger UI link](http://localhost:3000/swagger-ui.html). 


