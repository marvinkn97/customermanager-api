package dev.marvin.customermanager.customer.service;

import dev.marvin.customermanager.customer.dao.CustomerDao;
import dev.marvin.customermanager.customer.dto.CustomerRegistrationRequest;
import dev.marvin.customermanager.customer.dto.CustomerUpdateRequest;
import dev.marvin.customermanager.customer.model.Customer;
import dev.marvin.customermanager.exception.DuplicateResourceException;
import dev.marvin.customermanager.exception.RequestValidationException;
import dev.marvin.customermanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    //code to an interface and not concrete implementation
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier(value = "jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId)));
    }

    public void registerCustomer(CustomerRegistrationRequest request) {
        //check if email is taken
        String email = request.email();
        if (customerDao.existsCustomerWithEmail(email)) {
            throw new DuplicateResourceException("email already taken");
        }

        //save customer
        customerDao.saveCustomer(new Customer(request.name(), email, request.mobile()));
    }

    public void updateCustomer(Long customerId, CustomerUpdateRequest request) {
        Customer existingCustomer = customerDao.getCustomerById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId)));

        boolean changes = false;

        if (request.name() != null && !request.name().equals(existingCustomer.getName())) {
            existingCustomer.setName(request.name());
            changes = true;
        }

        if (request.email() != null && !request.email().equals(existingCustomer.getEmail())) {

            if (customerDao.existsCustomerWithEmail(request.email())) {
                throw new DuplicateResourceException("email already taken");
            }
            existingCustomer.setEmail(request.email());
            changes = true;
        }

        if (request.mobile() != null && !request.mobile().equals(existingCustomer.getMobile())) {
            existingCustomer.setMobile(request.mobile());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes");
        }

        customerDao.saveCustomer(existingCustomer);
    }

    public void deleteCustomerById(Long customerId) {
        customerDao.deleteCustomerById(customerId);
    }
}
