package dev.marvin.customermanager.customer.service;

import dev.marvin.customermanager.customer.dao.CustomerDao;
import dev.marvin.customermanager.customer.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private CustomerService underTest;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
    }

    @Test
    void canGetAllCustomers() {
        //when
        underTest.getAllCustomers();
        //then
        verify(customerDao).getAllCustomers();
    }

    @Test
    void canGetCustomerById() {
        //given
        long customerId = 1;
        Customer customer = new Customer(customerId,"Foo Bar", "foo@example.com", "0792000000");
        when(customerDao.getCustomerById(customerId)).thenReturn(Optional.of(customer));
        //when
        Customer actual = underTest.getCustomerById(1L);
        //then
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void registerCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }
}