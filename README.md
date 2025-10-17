# Java MVC JDBC Database Management Application

## Overview

This repository contains Java programs demonstrating database connectivity using **JDBC**, structured with the **Model-View-Controller (MVC)** architecture. It showcases how to perform **CRUD operations** on MySQL tables including Employee, Product, and Student datasets, using secure and efficient techniques such as **lambda expressions**, **transaction management**, and **parameterized SQL queries**.

---

## Features

### 1. Employee Data Fetching (`EmployeeFetch.java`)
- Connects to MySQL Employee table.
- Retrieves and displays all records using JDBC.
- Demonstrates basic SQL SELECT queries with `Statement` and `ResultSet`.

### 2. Product CRUD Operations (`ProductCRUD.java`)
- Menu-driven CRUD (Create, Read, Update, Delete) interface.
- Uses `PreparedStatement` for safe queries.
- Manages transactions with commit and rollback to ensure data consistency.
- Manipulates Product table columns: ProductID, ProductName, Price, Quantity.

### 3. Student Management with MVC (`model/Student.java`, `view/StudentView.java`, `controller/StudentController.java`)
- Structured using MVC design for clear separation of concerns.
- Supports adding, viewing, updating, deleting student records.
- Uses `PreparedStatement` and JDBC connection pooling via Controller.
- Console UI for interactive database management.

---

## Project Structure
```text
controller/          # Contains business logic and JDBC operations (StudentController.java)
model/               # Includes data entities representing tables (Student.java)
view/                # Handles user interaction interface and display (StudentView.java)
EmployeeFetch.java   # Standalone example for Employee table read operations
ProductCRUD.java     # Console application for Product CRUD operations with transactions
Main.java            # Entry point launching the Student Management MVC application
README.md            # Documentation and setup instructions
mysql-connector-j-9.4.0.jar (not included)  # MySQL JDBC driver - download separately and add to classpath
```

---

## Setup Instructions

## 1. Clone the repository:

git clone <repository-url>
cd <project-folder>


## 2. Download MySQL Connector/J:

Download [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and place `mysql-connector-j-9.4.0.jar` in the root project directory (do not commit this file).

## 3. Create Database and Tables in MySQL:
```sql

CREATE DATABASE companyDB;
USE companyDB;

CREATE TABLE Employee (
EmpID INT PRIMARY KEY,
Name VARCHAR(50),
Salary DOUBLE
);

CREATE TABLE Product (
ProductID INT PRIMARY KEY,
ProductName VARCHAR(100),
Price DOUBLE,
Quantity INT
);

CREATE TABLE Student (
StudentID INT PRIMARY KEY,
Name VARCHAR(50),
Department VARCHAR(50),
Marks INT
);
```

## 4. Set Environment Variables for Credentials:
```text
Linux/macOS:

     export DB_USER=your_mysql_username

     export DB_PASSWORD=your_mysql_password

Windows CMD:

    set DB_USER=your_mysql_username

    set DB_PASSWORD=your_mysql_password
```
---


## Best Practices Demonstrated

- Secure credential management via environment variables.
- Parameterized SQL prevents SQL injection.
- Transactional integrity for update/delete operations.
- MVC pattern for modular, testable code.
- Exception handling and proper JDBC resource cleanup.
