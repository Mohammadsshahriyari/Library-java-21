package org.josef.library;

import org.josef.library.entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int menu;
        int borrow_counter = 1, customer_counter = 1;
        Scanner input = new Scanner(System.in);
        // Librarians
        Employee librarian = new Employee(1, "Umar", "Draz", LocalDate.now(), "923219598676");
        // Customers
        Customer customer1 = new Customer(1, "Ali", "Raza", 0);
        Customer customer2 = new Customer(2, "Saqib", "Raza", 0);
        // Books
        Book book1 = new Book(1, "Algorithms to Live", "Kindle", LocalDate.of(2016, 4, 19), "Brian Christian and Tom Griffiths", "This book is about how computer algorithms can be applied to our everyday lives." + " It explores how we can use algorithms to solve problems and make better decisions.", 16.99f, 2.0f, 4.1f, true);
        Book book2 = new Book(2, "The Pragmatic Programmer", "Kindle", LocalDate.of(1999, 10, 20), "Andy Hunt & Dave Thomas", "Straight from the programming trenches, The Pragmatic Programmer cuts through the increasing specialization and technicalities" + " of modern software development to examine the core process--taking a requirement and producing working", 28.99f, 2.0f, 4.33f, true);
        // Create Library
        Library library = new Library(1, "Quid E Azam Library", librarian);
        // Adding Customers to Library
        library.addCustomer(customer1);
        library.addCustomer(customer2);

        library.addBook(book1);
        library.addBook(book2);

        System.out.println("+-------------------+");
        System.out.println("| Welcome E-Library |");
        System.out.println("+-------------------+");
        do {
            menu = showMenu(input);
            switch (menu) {
                case 1: {
                    System.out.println("Enter Book ID  ");
                    int book_id = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                    if (book_id > 0) {
                        Book foundBook = library.findById(book_id);
                        if (foundBook != null) {
                            System.out.println("Book found !");
                            System.out.println(foundBook);
                        } else {
                            System.out.println("Sorry , No book found with this Book ID");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter Book Title  ");
                    String title = input.nextLine();
                    if (!title.isEmpty()) {
                        Book foundBook = library.findByTitle(title);
                        if (foundBook != null) {
                            System.out.println("Book found !");
                            System.out.println(foundBook);
                        } else {
                            System.out.println("Sorry , No book found with this Book Title");
                        }
                    } else {
                        System.out.println("Invalid Book Title");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter Book Author Name ");
                    String author = input.nextLine();
                    if (!author.isEmpty()) {
                        Book foundBook = library.findByAuthor(author);
                        if (foundBook != null) {
                            System.out.println("Book found !");
                            System.out.println(foundBook);
                        } else {
                            System.out.println("Sorry , No book found with this Book Author");
                        }
                    } else {
                        System.out.println("Invalid Book Author Name");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter Book General Name  ");
                    String general = input.nextLine();
                    if (!general.isEmpty()) {
                        Book foundBook = library.findByGeneral(general);
                        if (foundBook != null) {
                            System.out.println("Book found !");
                            System.out.println(foundBook);
                        } else {
                            System.out.println("Sorry , No book found with this Book General Name");
                        }
                    } else {
                        System.out.println("Invalid Book General Name");
                    }
                    break;
                }
                case 5: {
                    library.displayAllBooks();
                    break;
                }
                case 6: {
                    System.out.println("Enter Customer ID ");
                    int customer_id = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                    if (customer_id > -1) {
                        Customer customer = library.findByID(customer_id);
                        if (customer != null) {
                            System.out.println("Enter Book ID");
                            int book_id = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                            if (book_id > 0) {
                                Book book = library.findById(book_id);
                                if (book != null) {
                                    if (book.isAvailable()) {
                                        System.out.println("Enter Total days to be borrowed ");
                                        int days = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                                        if (days > 0) {
                                            LocalDate returnDate = LocalDate.now().plusDays(days);
                                            BorrowBook borrowBook = new BorrowBook();
                                            borrowBook.setId(borrow_counter++);
                                            borrowBook.setBook(book);
                                            borrowBook.setCustomer(customer);
                                            borrowBook.setReturned(false);
                                            borrowBook.setIssue_date(LocalDate.now());
                                            borrowBook.setReturn_date(returnDate);
                                            borrowBook.setDescription("Borrow Book for the days of " + days + ".Return Date is " + returnDate.format(DateTimeFormatter.ISO_DATE));
                                            library.issueBook(borrowBook);
                                            library.makeBookUnavailable(book.getId(), false);
                                            System.out.println("Borrow Book Entry Saved !\n Enjoy The Book");
                                        }
                                    } else {
                                        System.out.println("Sorry this book currently unavailable for borrow\nBut Don't worry we have many other books please search");
                                    }
                                } else {
                                    System.out.println("Sorry , no book found with this ID");
                                }
                            }
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("Enter Borrow ID ");
                    int borrow_id = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                    if (borrow_id > 0) {
                        long customer_id = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                        Customer foundCustomer = library.findByID(customer_id);
                        if (foundCustomer != null) {
                            BorrowBook borrowedBook = library.findEntry(borrow_id, customer_id);
                            if (borrowedBook != null) {
                                System.out.println("Please rate the book between 1 - 5");
                                int rating = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
                                if (library.returnBook(borrowedBook, rating) == null) {
                                    System.out.println("Error While saving Book Return Entry !");
                                }
                            } else {
                                System.out.println("No borrowed book entry found in system !");
                            }
                        } else {
                            System.out.println("No Customer Found");
                        }

                    }
                    break;
                }
                case 8: {
                    System.out.println("Enter First Name");
                    String fname = input.nextLine();
                    System.out.println("Enter Last Name");
                    String lname = input.nextLine();
                    Customer newCustmer = new Customer(customer_counter++, fname, lname, 0);
                    if (library.addCustomer(newCustmer) != null) {
                        System.out.println("New Customer Saved !");
                    }
                    break;
                }
                case 9: {
                    library.showAllCustomers();
                    break;
                }
                case 10: {
                    library.showBorrowRegister();
                    break;
                }
                default: {
                    if (menu != 0) {
                        System.out.println("Invalid menu selection");
                    }
                }
            }
        } while (menu != 0);
    }

    /*
    Display program menu for selection of menu by customer.
    input parameter type of Scanner required.
    @param Scanner
    @return menu_no
     */
    private static int showMenu(Scanner input) {
        int menu_no = -1;
        System.out.println("+-------------------------------------+");
        System.out.println("| Library Management System Main Menu |");
        System.out.println("+-------------------------------------+");
        System.out.println("1) Search Book by Book ID");
        System.out.println("2) Search Book by Book Title");
        System.out.println("3) Search Book by Book Author");
        System.out.println("4) Search Book by Book General");
        System.out.println("5) Display All Books");
        System.out.println("6) Borrow Book");
        System.out.println("7) Return Book");
        System.out.println("8) Add Customer");
        System.out.println("9) Show All Customers");
        System.out.println("10) Show Borrow Register");
        System.out.println("0) Exit");
        System.out.println("\n\nEnter Menu # ....  ");
        menu_no = input.hasNextInt() ? Integer.parseInt(input.nextLine()) : -1;
        return menu_no;
    }
}