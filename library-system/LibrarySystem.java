package library;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}

public class LibrarySystem {

    static Map<String, Book> library = new HashMap<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        boolean running = true;

        while (running) {
            System.out.println("\n===== Library System Menu =====");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    System.out.println("Exiting the program. Thank you.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void addBook() {
        System.out.print("Enter book title: ");
        String title = input.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = input.nextLine().trim();

        System.out.print("Enter quantity: ");
        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Quantity must be a number.");
            input.nextLine();
            return;
        }

        int quantity = input.nextInt();
        input.nextLine();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        if (library.containsKey(title.toLowerCase())) {
            Book existingBook = library.get(title.toLowerCase());
            existingBook.quantity += quantity;
            System.out.println("Book already exists. Quantity updated successfully.");
        } else {
            Book newBook = new Book(title, author, quantity);
            library.put(title.toLowerCase(), newBook);
            System.out.println("Book added successfully.");
        }
    }

    public static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = input.nextLine().trim();

        if (!library.containsKey(title.toLowerCase())) {
            System.out.println("Error: This book does not exist in the library.");
            return;
        }

        System.out.print("Enter quantity to borrow: ");
        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Quantity must be a number.");
            input.nextLine();
            return;
        }

        int quantity = input.nextInt();
        input.nextLine();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        Book book = library.get(title.toLowerCase());

        if (book.quantity >= quantity) {
            book.quantity -= quantity;
            System.out.println("Book borrowed successfully.");
            System.out.println("Remaining quantity: " + book.quantity);
        } else {
            System.out.println("Error: Requested quantity is not available.");
        }
    }

    public static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = input.nextLine().trim();

        if (!library.containsKey(title.toLowerCase())) {
            System.out.println("Error: This book does not belong to the library system.");
            return;
        }

        System.out.print("Enter quantity to return: ");
        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Quantity must be a number.");
            input.nextLine();
            return;
        }

        int quantity = input.nextInt();
        input.nextLine();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        Book book = library.get(title.toLowerCase());
        book.quantity += quantity;

        System.out.println("Book returned successfully.");
        System.out.println("Updated quantity: " + book.quantity);
    }
}