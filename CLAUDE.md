# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

The config.json holds all available Slices. 

To find a certain slice, .slices/index.json can be used for a fast lookup - it provides a comprehensive overview of all Slices.

In addition, you can find all slice definitions in .slices/< context >/<slice name>/slice.json

## Build & Test Commands

```bash
# Build the project
./mvnw clean install

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=CreateCatalogEntryTest

# Run a single test method
./mvnw test -Dtest=CreateCatalogEntryTest#'Create Catalog Entry Test'

# Format code (Spotless with ktfmt)
./mvnw spotless:apply

# Check formatting
./mvnw spotless:check

# Start the application (uses TestContainers for Postgres)
# Run from src/test/kotlin/de/eventmodelers/ApplicationStarter.kt
```

## Architecture

This is a **Kotlin/Spring Boot Event Sourcing** application using **Axon Framework** with a **Vertical Slice Architecture**.

### Core Patterns

- **Event Sourcing**: State changes are captured as events using Axon Framework
- **CQRS**: Commands handled by aggregates, queries via read models
- **Vertical Slices**: Each feature is a self-contained slice with its own package

### Package Structure

```
de.eventmodelers/
├── common/          # Shared interfaces: Command, Event, ReadModel, Processor
├── domain/          # Aggregates (e.g., CatalogueManagementAggregate)
├── events/          # Domain events (e.g., CatalogueEntryCreatedEvent)
├── <feature>/       # Feature slices (e.g., catalog/)
│   └── <slice>/     # Individual slice (e.g., createcatalogentry/)
│       └── internal/    # Slice implementation (controllers, services)
└── support/         # Cross-cutting concerns (notifications, debugging)
```

### Key Conventions

- **Commands**: Located in `<feature>/domain/commands/<slice>/`, implement `Command` interface, use `@TargetAggregateIdentifier`
- **Events**: Located in `events/` package, implement `Event` interface
- **Aggregates**: Located in `domain/`, use `@Aggregate`, handle commands with `@CommandHandler`, apply events via `@EventSourcingHandler`
- **REST Controllers**: Located in `<slice>/internal/`, use `CommandGateway` to dispatch commands
- **Spring Modulith**: Modules defined via package structure, `common` and `domain` are shared modules

### Testing

- **Aggregate Tests**: Use Axon's `AggregateTestFixture` for given-when-then testing
- **Integration Tests**: Use `ApplicationStarter` which bootstraps TestContainers (Postgres)
- **RandomData**: Utility in `common/support/` for generating test data via EasyRandom
- **Module Verification**: `ModuleTest` verifies Spring Modulith boundaries

### Database

- PostgreSQL via TestContainers (dev) or direct connection (prod)
- Flyway migrations in `src/main/resources/db/migration/`
- Axon event store tables are auto-configured