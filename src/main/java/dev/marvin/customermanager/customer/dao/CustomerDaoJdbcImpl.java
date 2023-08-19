package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "jdbc")
public class CustomerDaoJdbcImpl implements CustomerDao{
    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Customer> getAllCustomers() {
        var sql = """
                SELECT * FROM tbl_customers
                """;
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {

        var sql = """
                INSERT INTO tbl_customers(name, email, mobile) VALUES(?, ?, ?)
                """;
       int rowsAffected =  jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getMobile());

        System.out.println("JdbcTemplate update = " + rowsAffected);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return false;
    }
}
