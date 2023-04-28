package org.josef.library.entities;

import lombok.Getter;
import lombok.Setter;
import org.josef.library.services.BorrowService;
import org.josef.library.services.CustomerService;
import org.josef.library.services.LibraryService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Library implements LibraryService, CustomerService, BorrowService {
    private long library_id;
    private String library_name;
    private final List<Book> books;
    private final List<Customer> customers;
    private List<BorrowBook> borrowBookList;
    private final Employee librarian;
    public float total_collection;
    public int total_borrowed_books;


    public Library(long library_id, String library_name, Employee librarian) {
        this.library_id = library_id;
        this.library_name = library_name;
        this.librarian = librarian;
        this.books = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.borrowBookList = new ArrayList<>();
        this.total_collection = 0f;
        this.total_borrowed_books = 0;
    }

    public Library(long library_id, String library_name, Employee librarian, List<Book> books) {
        this.library_id = library_id;
        this.library_name = library_name;
        this.librarian = librarian;
        this.books = books;
        this.customers = new ArrayList<>();
        this.borrowBookList = new ArrayList<>();
        this.total_collection = 0f;
        this.total_borrowed_books = 0;
    }

    public Library(long library_id, String library_name, Employee librarian, List<Book> books, List<Customer> customers) {
        this.library_id = library_id;
        this.library_name = library_name;
        this.librarian = librarian;
        this.books = books;
        this.customers = customers;
        this.borrowBookList = new ArrayList<>();
        this.total_collection = 0f;
        this.total_borrowed_books = 0;
    }

    public Library(long library_id, String library_name, Employee librarian, List<Book> books, List<Customer> customers, List<BorrowBook> borrowBooks) {
        this.library_id = library_id;
        this.library_name = library_name;
        this.librarian = librarian;
        this.books = books;
        this.customers = customers;
        this.borrowBookList = borrowBooks;
        this.total_collection = 0f;
        this.total_borrowed_books = 0;
    }


    @Override
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public boolean removeBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                books.remove(b);
                System.out.printf("%s with ID %d has been removed !%n", book.getTitle(), book.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public Book updateBook(Book book) {
        Book foundBook = findById(book.getId());
        if (foundBook != null) {
            int indexOf = books.indexOf(foundBook);
            books.add(indexOf, book);
            System.out.printf("%s with ID %d has been update !%n", book.getTitle(), book.getId());
            return book;
        }
        return null;
    }

    @Override
    public Book findById(long id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        System.out.printf("Book with ID %d not found !%n", id);
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.printf("Book with Title %s not found !%n", title);
        return null;
    }

    @Override
    public Book findByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        System.out.printf("Book with Author %s not found !%n", author);
        return null;
    }

    @Override
    public Book findByGeneral(String general) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(general)) {
                return book;
            }
        }
        System.out.printf("Book with General %s not found !%n", general);
        return null;
    }

    @Override
    public Book makeBookAvailable(long id, boolean available) {
        Book foundBook = findById(id);
        if (foundBook != null) {
            foundBook.setAvailable(available);
            return foundBook;
        }
        return null;
    }

    @Override
    public Book makeBookUnavailable(long book_id, boolean available) {
        Book foundBook = findById(book_id);
        if (foundBook != null) {
            for (Book b : books) {
                if (b.getId() == foundBook.getId()) {
                    b.setAvailable(available);
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public void displayAllBooks() {
        System.out.println("Below is the list of all books in this library");
        for (Book book : books) {
            System.out.println(book);
        }
    }


    @Override
    public Customer findByID(long id) {
        for (Customer customer : customers) {
            if (customer.getCustomer_id() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public int totalCustomer() {
        return customers.size();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        Customer foundCustomer = findByID(customer.getCustomer_id());
        if (foundCustomer != null) {
            customers.remove(foundCustomer);
            return true;
        }
        return false;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer foundCustomer = findByID(customer.getCustomer_id());
        if (foundCustomer != null) {
            int indexOf = customers.indexOf(foundCustomer);
            customers.add(indexOf, customer);
            return customer;
        }
        return null;
    }

    @Override
    public void showAllCustomers() {
        if (customers.size() > 0) {
            for (Customer customer : customers) {
                System.out.println("    Customer ID " + customer.getCustomer_id() + " First Name " + customer.getFname() + " Last Name " + customer.getLname() + " Total Books Reads " + customer.getTotal_books_read());
            }
        } else {
            System.out.println("Customer List is Empty");
        }
    }

    @Override
    public BorrowBook issueBook(BorrowBook borrowBook) {
        borrowBookList.add(borrowBook);
        return borrowBook;
    }

    @Override
    public BorrowBook returnBook(BorrowBook borrowBook, float rating) {
        for (BorrowBook borrow : borrowBookList) {
            if (borrow.getId() == borrowBook.getId()) {
                for (Book book : books) {
                    if (book.getId() == borrowBook.getBook().getId()) {
                        int indexOf = books.indexOf(book);
                        book.setAvailable(true);
                        book.setRating(rating);
                        books.add(indexOf, book);
                        borrow.setReturned(true);
                        borrow.setBook(book);
                        System.out.println("Book with Borrow ID " + borrow.getId() + " has been returned !");
                        return borrow;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BorrowBook findEntry(long borrow_id, long customer_id) {
        for (BorrowBook borrowBook : borrowBookList) {
            if (borrowBook.getId() == borrow_id && borrowBook.getCustomer().getCustomer_id() == customer_id) {
                return borrowBook;
            }
        }
        return null;
    }

    @Override
    public void showBorrowRegister() {
        if (borrowBookList.size() > 0) {
            System.out.println("    These are the entries of borrows books");
            for (BorrowBook borrowBook : borrowBookList) {
                System.out.println("    Borrow ID " + borrowBook.getId() + " Book Name " + borrowBook.getBook().getTitle() + " Customer ID " + borrowBook.getCustomer().getCustomer_id() + " Customer name " + borrowBook.getCustomer().getFname().concat(" ").concat(borrowBook.getCustomer().getLname()) + " Issue Date " + borrowBook.getIssue_date().format(DateTimeFormatter.ISO_DATE) + " Return Date " + borrowBook.getReturn_date().format(DateTimeFormatter.ISO_DATE));
            }
        } else {
            System.out.println("No Entries found in Register");
        }
    }
}
