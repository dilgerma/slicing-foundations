# Slice Builder - Master Skill

## Overview

This skill describes how to transform slice definitions from `config.json` into executable Kotlin code following the CQRS/Event Sourcing patterns with Axon Framework.

## Input: config.json

The `config.json` file at the project root contains an array of slice definitions:

```json
{
  "slices": [
    {
      "id": "...",
      "status": "Done|InProgress|Created",
      "title": "slice: <Name>",
      "context": "<BoundedContext>",
      "sliceType": "STATE_CHANGE|STATE_VIEW|UNDEFINED",
      "commands": [...],
      "events": [...],
      "readmodels": [...],
      "screens": [...],
      "processors": [...],
      "specifications": [...],
      "actors": [...],
      "aggregates": [...]
    }
  ]
}
```

## Slice Type Selection

Based on `sliceType`, use the appropriate skill:

| sliceType | Skill to Use | Description |
|-----------|--------------|-------------|
| `STATE_CHANGE` | [state-change-slice.md](./state-change-slice.md) | Commands that modify state via events |
| `STATE_VIEW` | [state-view-slice.md](./state-view-slice.md) | Read models that project events |
| `UNDEFINED` | Skip | Placeholder for future development |

If the slice contains `processors` with `type: "AUTOMATION"`, also apply:
- [automation-slice.md](./automation-slice.md) | Event-driven automation processors

## Context to Module Mapping

Map the `context` field to Kotlin package/module:

| Context | Module | Package |
|---------|--------|---------|
| Case Management | cases | `de.alex.cases` |
| Kanzlei verwaltung | lawfirm | `de.alex.lawfirm` |
| License Management | licensing | `de.alex.licensing` |
| Documents | documents | `de.alex.documents` |
| Tasks | tasks | `de.alex.tasks` |
| Appointments | appointments | `de.alex.appointments` |
| ERV | erv | `de.alex.erv` |
| ERV Rueckverkehr | erv | `de.alex.erv` |
| Template Rendering | templaterendering | `de.alex.templaterendering` |
| Submissions | submissions | `de.alex.submissions` |
| default | domain | `de.alex.domain` (shared) |

## Processing Order

When implementing a slice, follow this order:

### For STATE_CHANGE Slices:
1. **Commands** - Create command classes first
2. **Events** - Create event classes
3. **Aggregate Handlers** - Add @CommandHandler and @EventSourcingHandler
4. **Read Models** - Create entities if readmodels exist
5. **Projectors** - Create @EventHandler projectors
6. **Queries** - Create query classes and @QueryHandler
7. **Automations** - Create processors if `processors` array exists
8. **Migrations** - Create Flyway SQL migrations
9. **Tests** - Create aggregate tests from specifications

### For STATE_VIEW Slices:
1. **Read Models** - Create entities
2. **Projectors** - Create @EventHandler projectors
3. **Queries** - Create query classes and @QueryHandler
4. **Controllers** - Create REST endpoints if apiEndpoint specified
5. **Migrations** - Create Flyway SQL migrations
6. **Tests** - Create projection tests from specifications

## Field Type Mapping

### JSON to Kotlin Types
| config.json Type | Kotlin Type | Import |
|------------------|-------------|--------|
| UUID | UUID | `java.util.UUID` |
| String | String | - |
| Date | LocalDate | `java.time.LocalDate` |
| Integer | Int | - |
| Boolean | Boolean | - |
| Decimal | BigDecimal | `java.math.BigDecimal` |

### Cardinality
| Cardinality | Kotlin Pattern |
|-------------|----------------|
| Single | `T` or `T?` |
| Multiple | `List<T>` |

### Optional Fields
| optional | Kotlin Pattern |
|----------|----------------|
| true | `T?` (nullable) |
| false/absent | `T` (non-null) |

## Dependency Analysis

Each element has a `dependencies` array describing relationships:

```json
"dependencies": [
  {
    "id": "...",
    "type": "OUTBOUND|INBOUND",
    "title": "Element Name",
    "elementType": "COMMAND|EVENT|READMODEL|SCREEN|AUTOMATION"
  }
]
```

### Interpretation:
- **OUTBOUND + COMMAND**: This element triggers the command
- **OUTBOUND + EVENT**: This command emits the event
- **OUTBOUND + READMODEL**: This event updates the read model
- **OUTBOUND + SCREEN**: This read model is displayed on screen
- **INBOUND + EVENT**: This element is triggered by the event
- **INBOUND + COMMAND**: This event was emitted by the command
- **INBOUND + READMODEL**: This screen displays the read model
- **INBOUND + AUTOMATION**: This command is triggered by automation

## File Naming Conventions

### Commands
- Directory: `src/main/kotlin/de/alex/<module>/domain/commands/<commandname>/`
- File: `<CommandName>Command.kt`
- Example: `AssignClerkToCaseCommand.kt`

### Events
- Directory: `src/main/kotlin/de/alex/events/`
- File: `<EventName>Event.kt`
- Example: `ClerkAssignedToCaseEvent.kt`

### Read Models
- Directory: `src/main/kotlin/de/alex/<module>/<readmodelname>/`
- Entity: `<ReadModelName>ReadModel.kt`
- Repository: `internal/<ReadModelName>ReadModelRepository.kt`
- Projector: `internal/<ReadModelName>ReadModelProjector.kt`
- Query: `<ReadModelName>ReadModelQuery.kt`
- QueryHandler: `internal/<ReadModelName>ReadModelQueryHandler.kt`

### Processors
- Directory: `src/main/kotlin/de/alex/<module>/<processorname>/internal/`
- File: `<ProcessorName>Processor.kt`

### Migrations
- Directory: `src/main/resources/db/migration/`
- File: `V<version>__<description>.sql`

## Name Transformations

Convert slice titles to code identifiers:

| Slice Title | Class Name | Package Name |
|-------------|------------|--------------|
| "Assign Clerk to Case" | AssignClerkToCase | assignclerktocase |
| "Law Firm Created" | LawFirmCreated | lawfirmcreated |
| "Auto Deactivate License" | AutoDeactivateLicense | autodeactivatelicense |

Rules:
1. Remove "slice: " prefix
2. PascalCase for class names
3. lowercase no separators for package names
4. Append suffix: Command, Event, ReadModel, Processor as appropriate

## Quick Reference: Required Annotations

### Aggregates
```kotlin
@NoArgConstructor
@Aggregate
class MyAggregate {
    @AggregateIdentifier
    lateinit var aggregateId: UUID
}
```

### Commands
```kotlin
data class MyCommand(
    @TargetAggregateIdentifier var aggregateId: UUID,
    // fields...
) : Command
```

### Events
```kotlin
data class MyEvent(
    var aggregateId: UUID,
    // fields...
) : Event
```

### Projectors
```kotlin
@ProcessingGroup(ProcessingGroups.MODULE)
@Component
class MyProjector {
    @EventHandler
    fun on(event: MyEvent) { }
}
```

### Query Handlers
```kotlin
@Component
class MyQueryHandler {
    @QueryHandler
    fun handleQuery(query: MyQuery): MyReadModel? { }
}
```

### Automation Processors
```kotlin
@ProcessingGroup(ProcessingGroups.MODULE)
@Component
class MyProcessor : Processor {
    @DisallowReplay
    @EventHandler
    fun on(event: TriggerEvent) {
        commandGateway.send<TargetCommand>(...)
    }
}
```

## Workflow Example

Given a slice from config.json:
```json
{
  "title": "slice: Create Task",
  "context": "Tasks",
  "sliceType": "STATE_CHANGE",
  "commands": [{"title": "Create Task", ...}],
  "events": [{"title": "Task Created", ...}],
  "readmodels": [{"title": "Tasks List", ...}]
}
```

Generate files:
1. `src/main/kotlin/de/alex/tasks/domain/commands/createtask/CreateTaskCommand.kt`
2. `src/main/kotlin/de/alex/events/TaskCreatedEvent.kt`
3. Update `src/main/kotlin/de/alex/domain/TaskAggregate.kt`
4. `src/main/kotlin/de/alex/tasks/taskslist/TasksListReadModel.kt`
5. `src/main/kotlin/de/alex/tasks/taskslist/internal/TasksListReadModelProjector.kt`
6. `src/main/kotlin/de/alex/tasks/taskslist/TasksListReadModelQuery.kt`
7. `src/main/kotlin/de/alex/tasks/taskslist/internal/TasksListReadModelQueryHandler.kt`
8. `src/main/resources/db/migration/V<n>__create_tasks_list_read_model.sql`
