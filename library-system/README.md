# Library System

A menu driven Java console application for managing a basic library inventory. Users can add books, borrow available books, return books, and track the remaining quantity.

## Features

* Add new books with a title, author, and quantity
* Update the quantity when an existing book is added again
* Borrow books after checking inventory availability
* Return books and update the available quantity
* Find books without case sensitivity
* Validate menu selections and quantity entries
* Continue running until the user chooses to exit

## Java Concepts Demonstrated

* Object oriented programming with a `Book` class
* Constructors and object creation
* `HashMap` and `Map` collections
* Loops, conditional statements, and switch cases
* User input with `Scanner`
* Input validation and error handling

## Running the Program

```bash
javac -d . LibrarySystem.java
java library.LibrarySystem
```

The program stores information in memory, so the inventory resets when the application closes.
