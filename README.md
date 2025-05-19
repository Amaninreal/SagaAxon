# Saga Pattern Microservices with Axon Framework

This project demonstrates a microservices-based architecture for an **E-commerce Order Management System** using the **Saga pattern** with the **Axon Framework**. The system handles distributed transactions across multiple bounded contexts including **Order**, **Payment**, and **Shipping**, coordinated via sagas to ensure consistency.

## Project Structure
```
sagaaxon/
├── aws-common/ # AWS integrations (S3, config)
├── core-apis/ # Shared API commands and events
├── order-service/ # Order creation and management
├── payment-service/ # Payment and invoice processing
├── shipping-service/ # Shipping orchestration and delivery
├── pom.xml # Parent Maven configuration
└── README.md # Project documentation

```
---

## Tech Stack

- **Java 17+**
- **Spring Boot**
- **Axon Framework**
- **Maven**
- **OpenAPI (Swagger)**
- **S3 Integration (aws-common)**
- **CQRS & Event Sourcing**
- **Saga Pattern**

---

## Module Overview

### `aws-common/`
Reusable AWS services.
- `AwsConfig.java` - AWS configuration.
- `S3Service.java` - Handles S3 upload operations.

### `core-apis/`
Contains shared domain classes used by all services:
- `commands/` - CreateOrderCommand, CreateInvoiceCommand, etc.
- `events/` - OrderCreatedEvent, InvoiceCreatedEvent, etc.

### `order-service/`
Responsible for creating orders and orchestrating the saga.
- **Aggregates** - `OrderAggregate`
- **Controllers** - `OrderCommandController`, `OrderQueryController`
- **Sagas** - `OrderManagementSaga`
- **DTOs & Repos** - For command/query separation
- **Config** - Axon setup and Swagger

### `payment-service/`
Handles invoice creation and simulates payment logic.
- **Aggregates** - `InvoiceAggregate`
- **Controller** - `InvoiceController`
- **Services** - Command & query services for invoice
- **Handlers** - Event handling for invoice events

### `shipping-service/`
Handles shipping-related events.
- **Aggregates** - `ShippingAggregate`
- **Controller** - `ShippingController`
- **Axon Config & Swagger**

---

## Saga Flow

1. **Order Creation** → Triggers `OrderCreatedEvent`
2. Saga listens → Sends `CreateInvoiceCommand`
3. **Invoice Created** → `InvoiceCreatedEvent` triggers
4. Saga continues → Sends `CreateShippingCommand`
5. **Shipping Initiated** → `OrderShippedEvent`
6. **Saga Completed** → Order is marked as fulfilled

---

## How to Run

### Prerequisites
- Java 17+
- Maven
- Docker (for Axon Server if needed)

### Steps

```bash
# Clone the repo
git clone https://github.com/amaninreal/sagaaxon.git
cd sagaaxon

# Build all modules
mvn clean install

# Start individual services
cd order-service
mvn spring-boot:run

cd ../payment-service
mvn spring-boot:run

cd ../shipping-service
mvn spring-boot:run

```
---

## API Docs

Each service has OpenAPI/Swagger enabled:

- **Order Service**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **Payment Service**: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- **Shipping Service**: [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

---

## References

- [Axon Framework Docs](https://docs.axoniq.io)
- [CQRS & Event Sourcing Concepts](https://martinfowler.com/bliki/CQRS.html)

---