package org.josef.library;


import org.josef.library.entities.Book;
import org.josef.library.entities.Employee;
import org.josef.library.entities.Library;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AddBookTest {

    private Employee librarian = null;

    @Before
    public void init() {
        librarian = new Employee(1, "John", "Smith", LocalDate.now(), "xxxxxxxxxxx");

    }


    @Test
    public void addBook() {
        String library_name = "Asia Library";
        Library library = new Library(1, library_name, librarian);
        Book book = new Book(1, "Algorithms to Live", "Kindle", LocalDate.of(2016, 4, 19), "Brian Christian and Tom Griffiths", "This book is about how computer algorithms can be applied to our everyday lives." + " It explores how we can use algorithms to solve problems and make better decisions.", 16.99f, 2.0f, 4.1f, true);
        library.addBook(book);
        Assert.assertTrue(library.getBooks().get(0).getTitle().equalsIgnoreCase("algorithms to live"));
    }


    @After
    public void destroy() {

    }
}
