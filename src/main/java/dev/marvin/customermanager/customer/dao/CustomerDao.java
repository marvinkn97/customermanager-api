package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long customerId);
    void saveCustomer(Customer customer);
    void deleteCustomerById(Long customerId);
    boolean existsCustomerWithEmail(String email);
}
