package org.josef.library;


import org.josef.library.entities.Employee;
import org.josef.library.entities.Library;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CreateLibraryTest {

    private Employee librarian = null;

    @Before
    public void init() {
        librarian = new Employee(1,"John", "Smith", LocalDate.now(), "xxxxxxxxxxx");

    }


    @Test
    public void createLibrary() {
        String library_name = "Asia Library";
        Library library = new Library(1, library_name, librarian);
        Assert.assertTrue(library.getLibrary_name().equalsIgnoreCase("asia library"));
    }


    @After
    public void destroy() {

    }
}
