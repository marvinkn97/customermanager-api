package dev.marvin.customermanager.customer.repository;

import dev.marvin.customermanager.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
