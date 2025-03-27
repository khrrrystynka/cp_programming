package com.booklibrary;

import java.util.*;

public class Main {
    private static List<Book> books = LibraryManager.loadBooksFromFile();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nBook Library Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Update Book");
            System.out.println("5. Search Book");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> viewBooks();
                case 4 -> updateBook();
                case 5 -> searchBooksMenu();
                case 6 -> sortBooksMenu();
                case 7 -> {
                    LibraryManager.saveBooksToFile(books);
                    System.out.println("Exiting the application...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void removeBook() {
        System.out.print("Enter book ID to remove: ");
        int id = scanner.nextInt();
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed (if existed).");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void updateBook() {
        System.out.print("Enter book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book book : books) {
            if (book.getId() == id) {
                System.out.print("Enter new title: ");
                book.setTitle(scanner.nextLine());
                System.out.print("Enter new author: ");
                book.setAuthor(scanner.nextLine());
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    private static void searchBooksMenu() {
        System.out.println("Search books by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> searchByTitle();
            case 2 -> searchByAuthor();
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void searchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine().toLowerCase();
        books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title))
                .forEach(System.out::println);
    }

    private static void searchByAuthor() {
        System.out.print("Enter author to search: ");
        String author = scanner.nextLine().toLowerCase();
        books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author))
                .forEach(System.out::println);
    }

    private static void sortBooksMenu() {
        System.out.println("Sort books by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Date Added");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> books.sort(Comparator.comparing(Book::getTitle));
            case 2 -> books.sort(Comparator.comparing(Book::getAuthor));
            case 3 -> books.sort(Comparator.comparing(Book::getAddedDate));
            default -> {
                System.out.println("Invalid choice, sorting by Title.");
                books.sort(Comparator.comparing(Book::getTitle));
            }
        }
        System.out.println("Books sorted successfully.");
    }
}
