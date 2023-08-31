package dev.marvin.customermanager.customer.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void coreDomainTest(){

        Customer customer = new Customer("Foo Bar", "foobar@example.com", "254798523014");

        assertEquals("Foo Bar", customer.getName());
        assertEquals("foobar@example.com", customer.getEmail());
        assertEquals("254798523014", customer.getMobile());
    }
}