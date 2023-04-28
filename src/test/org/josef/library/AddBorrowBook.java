package org.josef.library;


import org.josef.library.entities.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBorrowBook {

    private Employee librarian = null;
    private Library library = null;
    private final String library_name = "Asia Library";
    private Customer customer;
    private Book book;

    @Before
    public void init() {
        librarian = new Employee(1, "John", "Smith", LocalDate.now(), "xxxxxxxxxxx");
        library = new Library(1, library_name, librarian);
        customer = new Customer(1, "Ali", "Raza", 0);
        book = new Book(1, "Algorithms to Live", "Kindle", LocalDate.of(2016, 4, 19), "Brian Christian and Tom Griffiths", "This book is about how computer algorithms can be applied to our everyday lives." + " It explores how we can use algorithms to solve problems and make better decisions.", 16.99f, 2.0f, 4.1f, true);
        library.addBook(book);
        library.addCustomer(customer);

    }


    @Test
    public void addBorrowBook() {
        Book book = library.findById(1);
        if (book != null) {
            if (book.isAvailable()) {
                System.out.println("Enter Total days to be borrowed ");
                int days = 2;
                if (days > 0) {
                    LocalDate returnDate = LocalDate.now().plusDays(days);
                    BorrowBook borrowBook = new BorrowBook();
                    borrowBook.setId(1);
                    borrowBook.setBook(book);
                    borrowBook.setCustomer(customer);
                    borrowBook.setReturned(false);
                    borrowBook.setIssue_date(LocalDate.now());
                    borrowBook.setReturn_date(returnDate);
                    borrowBook.setDescription("Borrow Book for the days of " + days + ".Return Date is " + returnDate.format(DateTimeFormatter.ISO_DATE));
                    library.issueBook(borrowBook);
                    library.makeBookUnavailable(book.getId(), false);
                    Assert.assertTrue(library.getBorrowBookList().get(0).getBook().getTitle().equalsIgnoreCase("algorithms to live"));
                }
            }
        }

    }


    @After
    public void destroy() {
        library.getCustomers().clear();
        library.getBooks().clear();
        library.getBorrowBookList().clear();
    }
}
