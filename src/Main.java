import java.time.LocalDateTime;
import java.util.*;

class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private LocalDateTime addedDate;

    public Book(String title, String author) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.addedDate = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public LocalDateTime getAddedDate() { return addedDate; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Added: " + addedDate;
    }
}

class LibraryApp {
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Book Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Update Book");
            System.out.println("5. Search Book");
            System.out.println("6. Sort Books by Title");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> viewBooks();
                case 4 -> updateBook();
                case 5 -> searchBook();
                case 6 -> sortBooks();
                case 7 -> {
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
        System.out.println("Book removed (if existed). ");
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

    private static void searchBook() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine().toLowerCase();
        books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title))
                .forEach(System.out::println);
    }

    private static void sortBooks() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title.");
    }
}
