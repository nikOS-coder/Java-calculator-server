# Java Multi-Threaded Network Calculator

A concurrent client-server calculator application implemented in **Java**. The server handles multiple simultaneous client connections using **Multi-threading**, parsing and evaluating complex mathematical expressions on-the-fly using a custom algorithm and data structures (`Stack`).

## Features

- **Concurrent Architecture:** The server spawns a dedicated thread (`ClientThread`) for each incoming client connection, allowing multiple users to calculate expressions simultaneously.
- **Custom Expression Evaluator:** Implements an infix expression evaluation engine using Stacks, supporting operator precedence (`+`, `-`, `*`, `/`), parentheses, and floating-point numbers.
- **Robust Error Handling:** Catches division-by-zero, syntax/expression errors, and connection drops gracefully without crashing the server.
- **Interactive CLI Client:** A command-line client that connects to the server and streams real-time mathematical queries.

## Tech Stack

- **Language:** Java
- **Networking:** `java.net` (Sockets, ServerSocket)
- **Concurrency:** Java Threads, Multithreading
- **Data Structures:** `java.util.Stack`

---

## Prerequisites & Dependencies

To compile and run this project, you only need the **Java Development Kit (JDK)** installed on your system.

---

## Compilation & Building

Navigate to the source directory and compile all Java files:

```bash
javac *.java
Usage / Running the Application
1. Start the Server
Run the server by providing an optional port number (defaults to 8888 if omitted):

Bash
java Server [PORT_NUMBER]
# Example:
java Server 8888
2. Connect a Client
In a separate terminal window, run the client by providing the server's port and IP address:

Bash
java Client [PORT_NUMBER] [IP_ADDRESS]
# Example:
java Client 8888 127.0.0.1
Once connected, type any mathematical expression (e.g., (5 + 3) * 2 / 4) and press Enter to get the evaluated result from the server in real time!

Project Structure
Server.c / Server.java — Listens for incoming socket connections and spawns worker threads.

ClientThread.java — Manages per-client communication lifecycle asynchronously.

Client.java — Interactive terminal client that sends expressions and prints results.

Calculator.java — Core evaluation engine utilizing stacks and precedence rules for arithmetic parsing.

Author
Christopoulos Nikolas