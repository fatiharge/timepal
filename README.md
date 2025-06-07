# TimePal - Application Architecture & Development Guide

This document outlines the technical architecture, layer structure, feature catalog, and development strategy for the **TimePal** project — a "rent-a-friend" app concept.

> Architecture style: **DDD + Feature-Based Hybrid**, modular mono-repo structure, single entry per ApplicationService, Minimum Viable Command principle.

---

## 🔄 Overview

* **Project Name**: TimePal *(subject to change)*
* **Stack**: Quarkus, Java 17+, Hibernate Panache, PostgreSQL, JWT, Maven
* **Architecture**: Feature-Based + DDD Hybrid, modular mono-repo, CLI & Dev UI
* **App Type**: Relationship-based service platform (swipe + service cart)

---

## 📌 Core Architectural Decisions

| Decision                    | Summary                                                      |
| --------------------------- | ------------------------------------------------------------ |
| Layers                      | API → Application → Domain → Infrastructure + Foundation     |
| ApplicationService Entry    | One `ApplicationService` per feature                         |
| Command Usage               | MVCmd: use immutable command only when needed                |
| Entity Separation           | Domain Entity ≠ Persistence Entity (mapped separately)       |
| I/O Model                   | Blocking I/O for now; reactive is a future option            |
| Event-driven                | Deferred; designed to be extensible later                    |
| Solo Developer Optimization | Minimize over-abstraction; favor readability and testability |

---

## 🔺 Folder Structure (Summary)

```bash
src/main/java/com/timepal
 ├── auth/
 │   ├── api/               # AuthController
 │   ├── application/
 │   │   ├── command/       # LoginCommand, RegisterCommand
 │   │   └── service/       # AuthApplicationService
 │   ├── domain/
 │   │   ├── model/         # UserCredentials
 │   │   └── service/       # AuthDomainService
 │   └── infrastructure/   # repositoryimpl, mappers

 ├── user/  # User profile, location, photo, services
 ├── swipe/
 ├── match/
 ├── cart/
 ├── order/
 ├── payment/
 ├── rating/
 ├── messaging/
 ├── notification/
 ├── settings/
 └── foundation/
     ├── exception/
     ├── validation/
     └── util/
```

---

## 🔹 Feature Catalog (Core Features)

→ Detailed tables and DB mappings are provided in the separate document: "Feature Catalog & DB Mapping"

1. **Auth** — Registration, login, JWT, refresh tokens
2. **User Profile** — Photos, services, location
3. **Discovery/Swipe** — Card swiping, filters
4. **Matchmaking** — Match creation from mutual likes
5. **Cart** — Service selection, cart logic
6. **Order** — Finalizing cart, scheduling meetups
7. **Payment** — Payment authorization, billing
8. **Messaging** — In-app chat for matched users
9. **Rating** — User reviews and ratings
10. **Notification** — In-app and push notifications
11. **Settings** — Privacy, preferences, account settings

---

## 🌐 Running the Project

### Development Mode

```bash
./mvnw quarkus:dev
```

### Package the App

```bash
./mvnw package
```

### Uber-JAR

```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

### Native Binary

```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

---

> Dev UI is available at: [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/) (only in dev mode)

---

Prepared by: [@fatiharge](https://github.com/fatiharge) • 2025
