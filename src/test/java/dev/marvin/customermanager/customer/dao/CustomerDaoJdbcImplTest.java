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
        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();


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
    void willReturnNotPresentWhenGetCustomerById() {
        //given
        long customerId = -1;

        //when
        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isNotPresent();
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
    void canUpdateCustomerName() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        //given
        String newName = faker().name().fullName();

        Customer update = new Customer();
        update.setName(newName);
        update.setId(customerId);

        underTest.updateCustomer(update);

        //when
        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(newName);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getMobile()).isEqualTo(customer.getMobile());
        });
    }

    @Test
    void canUpdateCustomerEmail() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        String newEmail = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 3);
        Customer update = new Customer();
        update.setEmail(newEmail);
        update.setId(customerId);

        underTest.updateCustomer(update);

        //when
        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(newEmail);
            assertThat(c.getMobile()).isEqualTo(customer.getMobile());
        });
    }

    @Test
    void canUpdateCustomerMobile() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        String newMobile = faker().phoneNumber().cellPhone();
        Customer update = new Customer();
        update.setMobile(newMobile);
        update.setId(customerId);

        underTest.updateCustomer(update);

        //when
        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getMobile()).isEqualTo(newMobile);
        });

    }

    @Test
    void canUpdateAllCustomerFields() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        //when update with all fields present
        Customer update = new Customer();
        update.setId(customerId);
        update.setName(faker().name().fullName());
        update.setEmail(faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 3));
        update.setMobile(faker().phoneNumber().cellPhone());

        underTest.updateCustomer(update);

        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(update.getName());
            assertThat(c.getEmail()).isEqualTo(update.getEmail());
            assertThat(c.getMobile()).isEqualTo(update.getMobile());
        });
    }

    @Test
    void willNotUpdateWhenNothingToUpdate() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        Customer update = new Customer();
        update.setId(customerId);
        underTest.updateCustomer(update);

        //when
        var actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getMobile()).isEqualTo(customer.getMobile());
        });
    }

    @Test
    void canDeleteCustomerById() {
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 5);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.insertCustomer(customer);

        //given
        long customerId = underTest.getAllCustomers().stream().filter(c -> c.getEmail().equals(email)).findFirst().map(Customer::getId).orElseThrow();

        //when
        underTest.deleteCustomerById(customerId);

        //then
        Optional<Customer> actual = underTest.getCustomerById(customerId);
        assertThat(actual).isNotPresent();
    }

    @Test
    void willReturnNotPresentWhenDeleteCustomerById() {
        //given
        long customerId = -1;

        //when
        underTest.deleteCustomerById(customerId);

        var actual = underTest.getCustomerById(customerId);

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

    @Test
    void willReturnFalseWhenEmailNotTaken() {
        //given
        String email = "nottaken@example.com";

        //when
        var actual = underTest.existsCustomerWithEmail(email);

        //then
        assertThat(actual).isFalse();
    }
}