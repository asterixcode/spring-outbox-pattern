# spring-outbox-pattern

## Description

How to solve the `Dual Write problem` using the `Outbox Pattern` with a pull and publish scheduler.

## Dual Write problem

This project is a simple solution for the `Dual Write problem`. 

The `Dual Write problem` happens when:

- the application needs to write to two different systems in a single transaction
- the systems have different transactional boundaries
- if one of the writes fails, the other write can't be rolled back, leaving the system in an inconsistent state

Example: 

Write to a database and to a message broker.

The `Dual Write problem` can be solved using the `Outbox Pattern`, which makes sure that if one of the writes fails, the system can still recover the state inside the transaction and roll back all the writes.

### Outbox Pattern implementation

There are probably different ways to implement the `Outbox Pattern`, but this simple project implements it using:

- a database table called `outbox` that contains all the messages that need to be sent to the message broker
- a scheduled task that reads the messages from the `outbox` table and sends them to the message broker

Then, once a service needs to write to the `orders` table, for example, it also writes to the `outbox` table in the same transaction.

## Technologies used

- Java 21
- Spring Boot 3.3.2
- Spring Data JDBC
- PostgreSQL
- Docker Compose
- Kafka
