package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.domain.Customer;
import dev.marvin.customermanager.customer.rowmapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier(value = "jdbc")
@Repository
public class CustomerDaoJdbcImpl implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerDaoJdbcImpl(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = """
                SELECT id, name, email, mobile
                FROM
                tbl_customers
                """;
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public void insertCustomer(Customer customer) {
        String sql = """
                INSERT INTO tbl_customers(name, email, mobile)
                VALUES(?, ?, ?)
                """;
        int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getMobile());
        System.out.println("Jdbc Insert result = " + rowsAffected);
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomerById(Long customerId) {

    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return false;
    }
}
