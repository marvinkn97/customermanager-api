package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.AbstractTestContainersTest;
import dev.marvin.customermanager.customer.domain.Customer;
import dev.marvin.customermanager.customer.rowmapper.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDaoJdbcImplTest extends AbstractTestContainersTest {
    CustomerDaoJdbcImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerDaoJdbcImpl(new JdbcTemplate(dataSource()), new CustomerRowMapper());
    }

    @Test
    void canGetAllCustomers() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        //when
        List<Customer> actual = underTest.getAllCustomers();

        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void canGetCustomerById() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        //given
        long customerId = underTest.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .map(Customer::getId)
                .orElseThrow();


        //when
        Optional<Customer> actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getMobile()).isEqualTo(customer.getMobile());
        });
    }

    @Test
    void canInsertCustomer() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);
        //when

        Optional<Customer> actual = underTest.getAllCustomers().stream().filter(c -> customer.getEmail().equals(email)).findFirst();

        //then
        assertThat(actual).isPresent();
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void canDeleteCustomerById() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        //given
        long customerId = underTest.getAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .map(Customer::getId)
                .orElseThrow();

        underTest.deleteCustomerById(customerId);

        //when
        Optional<Customer> actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isNotPresent();
    }

    @Test
    void existsCustomerWithEmail() {

        //given
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        //when
        boolean actual = underTest.existsCustomerWithEmail(email);

        //then
        assertThat(actual).isTrue();
    }
}