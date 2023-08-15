package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.entity.Customer;
import dev.marvin.customermanager.customer.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "jpa")
public class CustomerDaoJpaImpl implements CustomerDao{

    private final CustomerRepository customerRepository;

    public CustomerDaoJpaImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }
}
