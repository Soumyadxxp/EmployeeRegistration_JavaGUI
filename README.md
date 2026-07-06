# Employee Registration System

A Java Swing desktop application for managing employee records with XML-based data storage. The application allows users to register employee details, store records in memory, save them to an XML file, and view all registered employees.

---

# Features

## Employee Registration

* Register new employees
* Enter employee code
* Enter employee name
* Enter basic salary
* Enter department name
* Store employee records in memory
* Save records to an XML file
* Display all employee records
* Exit the application

---

# Employee Information

The application stores the following details:

* Employee Code
* Employee Name
* Basic Salary
* Department Name

---

# XML Data Storage

The application uses XML as its storage format.

Features include:

* Create an XML file automatically
* Store multiple employee records
* Load existing employee records at startup
* Read employee data from XML
* Write employee data using the Java DOM API

---

# Report View

The application provides a report screen that displays:

* Employee Code
* Employee Name
* Basic Salary
* Department Name

Additional features:

* Scrollable report area
* Switch between entry form and report view
* Read-only employee report

---

# Technologies Used

* Java
* Java Swing
* AWT
* DOM XML Parser
* XML Transformer API
* Collections Framework (ArrayList)
* Event Handling
* Object-Oriented Programming

---

# XML File Structure

Employee records are stored in an XML file named:

```text
Employee.xml
```

Example structure:

```xml
<employee>
    <emp>
        <code>101</code>
        <name>JOHN</name>
        <sal>50000</sal>
        <dept>IT</dept>
    </emp>
</employee>
```

---

# User Interface

The application provides:

* Employee Code field
* Employee Name field
* Basic Salary field
* Department Name field
* Submit button
* Commit button
* Show button
* Back button
* Exit button
* Employee report area

---

# Application Workflow

1. Launch the application.
2. Enter employee information.
3. Click **Submit** to add the employee to memory.
4. Repeat for additional employees.
5. Click **Commit** to save all records to the XML file.
6. Click **Show** to display all employee records.
7. Click **Back** to return to the registration form.
8. Exit the application when finished.

---

# How to Run

1. Install JDK 8 or later.
2. Open the project in your preferred Java IDE.
3. Compile the project.
4. Run `MainClass.java`.
5. The application automatically creates or reads `Employee.xml` from the project directory.

---

# Learning Objectives

This project demonstrates:

* Java Swing GUI Development
* Object-Oriented Programming
* Java Collections (ArrayList)
* XML File Handling
* DOM Parser
* XML Transformer API
* Event-Driven Programming
* File Handling
* Input Validation
* Desktop Application Development

---

# Author

Soumyadeep Basu

---

# License

This project is intended for educational and learning purposes. You are free to modify and extend it for personal or academic use.
