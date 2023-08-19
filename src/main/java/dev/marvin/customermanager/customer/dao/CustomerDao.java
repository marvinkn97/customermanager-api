package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long customerId);
    void saveCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    boolean existsCustomerWithEmail(String email);
}
