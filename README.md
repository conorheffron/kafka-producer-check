# kafka-producer-check

[![Java CI with Gradle](https://github.com/conorheffron/kafka-producer-check/actions/workflows/gradle.yml/badge.svg)](https://github.com/conorheffron/kafka-producer-check/actions/workflows/gradle.yml)

## Tech
- JDK 17, Apache Kafka 3 (Client Libs), & Kafka Broker

## Test Topic Creation & Consumer Subscription from Kafka Broker
```shell
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic ironoc-test-topic

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ironoc-test-topic --from-beginning
```

## Build Project
```shell
./gradlew clean build
```

## Run Kafka Consumer
```shell
java -jar build/libs/kafka-producer-check-1.0.2.jar localhost:9092 ironoc-test-topic
```

## Enter `key` value & then `value` to send as message
![kafka-producer-key-then-message.png](src/test/resources/kafka-producer-key-then-message.png)

### Further logs
![kafka-producer-app-logs](src/test/resources/kafka-producer-app-logs.png)

## Exit Application (Enter `exit` in console, highlighted in `GREEN`)
```shell
exit
```

## Verify Messages Sent & Received by default plain text consumer
![kafka-consumer-running-in-broker](src/test/resources/kafka-consumer-running-in-broker.png)
