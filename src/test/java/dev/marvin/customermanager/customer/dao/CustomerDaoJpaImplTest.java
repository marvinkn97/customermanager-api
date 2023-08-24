package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.model.Customer;
import dev.marvin.customermanager.customer.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CustomerDaoJpaImplTest {
    private CustomerDaoJpaImpl underTest;
    private AutoCloseable autoCloseable;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerDaoJpaImpl(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCustomers() {
        //when
        underTest.getAllCustomers();
        //then
        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById() {
        //given
        long customerId = 1;
        //when
        underTest.getCustomerById(customerId);
        //then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void saveCustomer() {
        //given
        Customer customer = new Customer("Foo Bar", "foo@example.com", "0792000000");
        //when
        underTest.saveCustomer(customer);
        //then
        verify(customerRepository).save(customer);
    }

    @Test
    void deleteCustomerById() {
        //given
        long customerId = 1;
        //when
        underTest.deleteCustomerById(customerId);
        //then
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void existsCustomerWithEmail() {
        //given
        String email = "foo@example.com";
        //when
        underTest.existsCustomerWithEmail(email);
        //then
        verify(customerRepository).existsCustomerByEmail(email);
    }
}