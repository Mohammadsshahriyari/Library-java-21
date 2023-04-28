package org.josef.library;


import org.josef.library.entities.Employee;
import org.josef.library.entities.Library;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CreateEmployeeTest {


    @Before
    public void init() {

    }


    @Test
    public void createLibrary() {
        Employee librarian = new Employee(1, "John", "Smith", LocalDate.now(), "xxxxxxxxxxx");
        Assert.assertTrue(librarian.getFname().concat(" ").concat(librarian.getLname()).equalsIgnoreCase("john smith"));
    }


    @After
    public void destroy() {

    }
}
