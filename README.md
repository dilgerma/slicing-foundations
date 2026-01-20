Event Sourcing Workshop

## Setup
Slices are defined as packages in the root package (as specified in the generator).

Find the exercises here:
https://nebulit-exercises.vercel.app/

## Todos after the initial generation
The code contains TODOs that mark the places which need to be adapted. The generator makes certain basic assumptions (for example, aggregate IDs are UUIDs).

If these assumptions are changed, the code may not compile immediately and will need minor adjustments.

Your code guidelines take precedence, so it is expected that the code does not compile right away (however, only small adjustments should be necessary).

## Starting the application
To start the service, the ApplicationStarter class in src/test/kotlin can be used. Why is it located in test?

This class starts the complete environment (including Postgres and, if necessary, Kafka via TestContainers).

## Package structure

Events are located in the events package.

Aggregates are located in the domain package.

Each slice has its own isolated package.

The common package contains several interfaces for the general structure.