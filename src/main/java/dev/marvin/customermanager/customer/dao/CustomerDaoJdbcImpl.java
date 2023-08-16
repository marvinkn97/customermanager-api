package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.entity.Customer;

import java.util.List;
import java.util.Optional;


public class CustomerDaoJdbcImpl implements CustomerDao{
    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return false;
    }
}
