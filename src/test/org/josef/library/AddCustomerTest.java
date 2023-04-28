package org.josef.library;


import org.josef.library.entities.Book;
import org.josef.library.entities.Customer;
import org.josef.library.entities.Employee;
import org.josef.library.entities.Library;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AddCustomerTest {

    private Employee librarian = null;

    @Before
    public void init() {
        librarian = new Employee(1, "John", "Smith", LocalDate.now(), "xxxxxxxxxxx");

    }


    @Test
    public void addCustomer() {
        String library_name = "Asia Library";
        Library library = new Library(1, library_name, librarian);
        Customer customer = new Customer(1, "Ali", "Raza", 0);
        library.addCustomer(customer);
        Customer savedCustomer = library.getCustomers().get(0);
        Assert.assertTrue(savedCustomer.getFname().concat(" ").concat(savedCustomer.getLname()).equalsIgnoreCase("ali raza"));
    }


    @After
    public void destroy() {

    }
}
