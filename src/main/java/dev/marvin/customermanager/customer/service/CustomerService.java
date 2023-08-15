package dev.marvin.customermanager.customer.service;

import dev.marvin.customermanager.customer.dao.CustomerDao;
import dev.marvin.customermanager.customer.entity.Customer;
import dev.marvin.customermanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier(value = "jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }
    public Customer getCustomerById(Long customerId){
        return customerDao.getCustomerById(customerId).orElseThrow(()->
                new ResourceNotFoundException("Customer with id [%s] not found".formatted(customerId)));
    }
    public Customer saveCustomer(Customer customer){
        return null;
    }
    public void deleteCustomer(Customer customer){}
}
