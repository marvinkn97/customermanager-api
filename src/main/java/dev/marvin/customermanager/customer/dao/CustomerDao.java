package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long customerId);
    void createCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
    void updateCustomer(Customer customer);
}
