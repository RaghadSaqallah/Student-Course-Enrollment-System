# 🎓 Student Course Enrollment System

A desktop application built with **JavaFX** and **MySQL** that allows managing student course enrollments through a clean, modern UI.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Author](#author)

---

## Overview

This application provides a graphical interface to manage enrollment records that link students to courses. It supports full **CRUD** operations (Create, Read, Update, Delete) and includes input validation and duplicate-entry prevention.

---

## Features

- ✅ Add a new enrollment record (Student + Course + Date)
- ✅ Update an existing enrollment record
- ✅ Delete an enrollment record (with confirmation dialog)
- ✅ View all enrollment records in a table
- ✅ Duplicate enrollment detection
- ✅ Input validation before any database operation
- ✅ Auto-fill form fields when selecting a row from the table
- ✅ Modern, styled UI using custom CSS

---

## Project Structure

```
src/
├── app/
│   └── Main.java                   # Application entry point
│
├── config/
│   └── DBConnection.java           # Singleton database connection manager
│
├── models/
│   ├── student.java                # Student model (student_id, name, major)
│   ├── courses.java                # Course model (course_id, course_name)
│   └── enroll.java                 # Enrollment model (enroll_id, student_id, course_id, enrollDate)
│
├── dao/
│   ├── studentDAO.java             # Fetches student IDs from DB
│   ├── coursesDAO.java             # Fetches course IDs from DB
│   └── enrollDAO.java              # CRUD operations for enrollments
│
├── controllers/
│   └── EnrollmentController.java   # JavaFX controller for the enrollment screen
│
└── views/
    └── enroll.fxml                 # FXML layout for the enrollment UI

resources/
└── Styles/
    └── style.css                   # Custom CSS styles for the UI
```

---

## Prerequisites

| Requirement | Version |
|---|---|
| Java JDK | 11 or higher |
| JavaFX SDK | 17+ |
| MySQL Server | 5.7 or higher |
| MySQL Connector/J | 8.x |
| IDE | NetBeans / IntelliJ / Eclipse |

---

## Database Setup

1. Open your MySQL client and create the database:

```sql
CREATE DATABASE students;
USE students;
```

2. Create the required tables:

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    major      VARCHAR(100)
);

CREATE TABLE courses (
    course_id   INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL
);

CREATE TABLE enroll (
    enroll_id  INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id  INT NOT NULL,
    enroll_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id)  REFERENCES courses(course_id)
);
```

3. Insert some sample data:

```sql
INSERT INTO student (name, major) VALUES
('Alice Johnson', 'Computer Science'),
('Bob Smith', 'Mathematics'),
('Sara Ahmed', 'Physics');

INSERT INTO courses (course_name) VALUES
('Introduction to Programming'),
('Data Structures'),
('Linear Algebra');
```

> **Note:** The default DB connection uses `root` with no password on `localhost:3306`.  
> To change this, edit the constants in `config/DBConnection.java`:
> ```java
> private static final String URL      = "jdbc:mysql://localhost:3306/students";
> private static final String USER     = "root";
> private static final String PASSWORD = "";
> ```

---

## How to Run

1. Clone or download the project.
2. Open it in your IDE (e.g., NetBeans).
3. Add the **MySQL Connector/J** JAR and **JavaFX SDK** libraries to the project classpath.
4. Make sure MySQL is running and the database is set up as described above.
5. Run `Main.java` as the application entry point.

---

## Usage Guide

| Action | How to do it |
|---|---|
| **Add** | Select a Student ID, Course ID, and Enrollment Date → Click **➕ Add Enrollment** |
| **View** | Click **View** to load all records into the table |
| **Update** | Click a row in the table → Edit the fields → Click **Update** |
| **Delete** | Click a row in the table → Click **Delete** → Confirm the dialog |
| **Clear** | Fields are automatically cleared after Add/Update/Delete |

> ⚠️ Attempting to enroll the same student in the same course twice will show a **Duplicate Entry** warning.

---

## Author

**Raghad Saqallah**
