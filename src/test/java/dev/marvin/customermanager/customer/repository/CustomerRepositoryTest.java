package dev.marvin.customermanager.customer.repository;

import dev.marvin.customermanager.AbstractTestContainersTest;
import dev.marvin.customermanager.customer.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestContainersTest {

    @Autowired
    private  CustomerRepository underTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void existsCustomerByEmail() {
        //given
        String email = faker().internet().safeEmailAddress() + "_" + UUID.randomUUID().toString().substring(0, 6);
        Customer customer = new Customer(faker().name().fullName(), email, faker().phoneNumber().cellPhone());
        underTest.save(customer);
        //when
        boolean actual = underTest.existsCustomerByEmail(email);
        //then
        assertThat(actual).isTrue();
    }
}