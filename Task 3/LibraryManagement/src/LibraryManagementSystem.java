import java.util.*;

// Book class: represents each book in the library
class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public void issue() { this.isIssued = true; }
    public void returnBook() { this.isIssued = false; }

    @Override
    public String toString() {
        return "Book ID: " + id +
                ", Title: " + title +
                ", Status: " + (isIssued ? "Issued" : "Available");
    }
}

// User class: represents a person who can borrow books
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "User ID: " + id + ", Name: " + name;
    }
}

// Library class: Manages users and books
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void addUser(User user) { users.add(user); }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : books) System.out.println(b);
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in library.");
            return;
        }
        for (User u : users) System.out.println(u);
    }

    public Book findBookById(int bookId) {
        for (Book b : books) if (b.getId() == bookId) return b;
        return null;
    }
    public User findUserById(int userId) {
        for (User u : users) if (u.getId() == userId) return u;
        return null;
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book already issued!");
            return;
        }
        book.issue();
        System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName() + ".");
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book was not issued!");
            return;
        }
        book.returnBook();
        System.out.println("Book '" + book.getTitle() + "' returned.");
    }
}

// Main class: CLI for Library Management System
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. List Books");
            System.out.println("4. List Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String bookTitle = sc.nextLine();
                    library.addBook(new Book(bookId, bookTitle));
                    System.out.println("Book added!");
                    break;
                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = sc.nextLine();
                    library.addUser(new User(userId, userName));
                    System.out.println("User added!");
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    library.listUsers();
                    break;
                case 5:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt();
                    library.returnBook(returnBookId);
                    break;
                case 7:
                    System.out.println("Exiting System. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
