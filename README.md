# TSP Application

This is a Java Spring Boot application that solves the **Travelling Salesman Problem (TSP)** using a greedy approach. It allows users to provide a list of cities and returns an optimized path that visits all cities exactly once and minimizes the total travel distance.

## Features

- Solves the Travelling Salesman Problem using a greedy algorithm.
- Accepts a list of cities via an HTTP POST request.
- Returns the optimized path in which cities are ordered to minimize the travel distance.
- Randomly generates a distance matrix between cities for simulation purposes.

## Installation

Follow the steps below to set up the project on your local machine:

### 1. Clone the Repository

Clone this repository using Git by running the following command in your terminal:

```bash
git clone https://github.com/boykevich/tspapp.git
```

### 2. Navigate to the Project Directory

Change into the project directory:

```bash
cd tspapp
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

Run the application directly from your IDE by executing the main class or alternatively:

```bash
mvn spring-boot:run
```
