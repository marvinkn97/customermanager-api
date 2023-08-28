package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.AbstractTestContainersTest;
import dev.marvin.customermanager.customer.domain.Customer;
import dev.marvin.customermanager.customer.rowmapper.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDaoJdbcImplTest extends AbstractTestContainersTest {
    CustomerDaoJdbcImpl underTest;
    @MockBean
    CustomerRowMapper customerRowMapper;

    @BeforeEach
    void setUp() {
        underTest = new CustomerDaoJdbcImpl(new JdbcTemplate(dataSource()), customerRowMapper);
    }

    @Test
    void canGetAllCustomers() {
        Customer customer = new Customer(faker().name().fullName(), faker().internet().safeEmailAddress(), faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);
        //when
        List<Customer> actual =  underTest.getAllCustomers();
        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        //given
        //when
        //then
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void existsCustomerWithEmail() {
    }
}