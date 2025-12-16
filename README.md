> This project was originally developed as part of a university course.
> This repository is a public, standalone copy created for portfolio purposes.

# Collaborative Task Time Manager

## Overview

Collaborative Task Time Manager is an Android application designed to support task management in small collaborative groups by using **time spent** as the core metric for balancing workload. Instead of simply marking tasks as done, users register how much time they invested, allowing the application to compute fair and transparent task distribution among group members.

The application is particularly suited for shared environments such as student flats, shared housing, or small teams, where responsibilities should be distributed equitably over time.

This project was developed as part of a university Software Engineering course.

---

## Key Features

* **User authentication**: Registration and login system for secure access
* **Group management**: Create, join, and leave independent collaborative groups
* **Task registration**: Add tasks with title, description, time spent, participants, and affected members
* **Time-based balancing**: Automatic computation of time deficits and surpluses per user
* **Global summary view**: Clear overview of group effort distribution and identification of the next responsible member
* **Task history**: Chronological record of all tasks within each group
* **Optional enhancements**:

  * Predefined (template) tasks
  * Subtasks for finer-grained task modeling

---

## Application Flow

1. **Register / Log in**: Users authenticate to access the application
2. **Home screen**: Displays all groups the user belongs to
3. **Group view**: Shows the task history and available group actions
4. **Task creation**: Users register completed tasks with detailed time and participant information
5. **Global summary**: Computes total time invested per user and highlights workload imbalances

---

## Technical Overview

* **Platform**: Android
* **Language**: Java
* **Development environment**: Android Studio
* **Data persistence**: Database-backed storage for users, groups, and tasks
* **Data structures**: Tasks are stored using a stack-based structure to preserve chronological order
* **Permissions model**: All users within a group have equal administrative privileges

The system was designed with simplicity, maintainability, and clarity in mind, prioritizing usability and transparent data handling.

---

## Fairness Model

Each task specifies:

* Users who **performed** the task
* Users who **benefit** from the task

Time spent on a task is distributed among the affected users, allowing the system to compute a realistic balance of effort. This model supports scenarios where not all tasks impact all members equally.

---

## Project Context

This repository is a fork of the original course project repository. It is maintained here for personal portfolio and learning purposes.

---

## Contributors

* Miguel Ángel Caballero García
* Sol Cosculluela Alonso
* Miquel Martínez Gavagnin-Capoggiani
* Martí Batista Obiols

---

## License

This project was developed for academic purposes. Licensing and redistribution are subject to the original course repository terms.
