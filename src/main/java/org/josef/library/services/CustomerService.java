package org.josef.library.services;

import org.josef.library.entities.Customer;

public interface CustomerService {

    int totalCustomer();

    Customer findByID(long id);

    Customer addCustomer(Customer customer);

    boolean removeCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void showAllCustomers();

}
