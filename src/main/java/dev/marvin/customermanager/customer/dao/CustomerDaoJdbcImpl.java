package dev.marvin.customermanager.customer.dao;

import dev.marvin.customermanager.customer.model.Customer;
import dev.marvin.customermanager.customer.rowmapper.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "jdbc")
public class CustomerDaoJdbcImpl implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerDaoJdbcImpl(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }


    @Override
    public List<Customer> getAllCustomers() {
        var sql = """
                SELECT id, name, email, mobile FROM tbl_customers
                """;
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public void createCustomer(Customer customer) {

        var sql = """
                INSERT INTO tbl_customers(name, email, mobile) VALUES(?, ?, ?)
                """;
        int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getMobile());

        System.out.println("JdbcTemplate update = " + rowsAffected);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        var sql = """
                 SELECT id, name, email, mobile FROM tbl_customers WHERE id = ?
                """;

        return jdbcTemplate.query(sql, customerRowMapper, customerId).stream().findFirst();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        var sql = """
                DELETE FROM tbl_customers WHERE id = ?
                """;
        int rowsAffected = jdbcTemplate.update(sql, customer.getId());

        System.out.println("JdbcTemplate delete = " + rowsAffected);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {

        var sql = """
                SELECT count(*) FROM tbl_customers WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer.getName() != null) {
            var sql = """
                    UPDATE tbl_customers SET name = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getId());
            System.out.println("Update customer name result =  " + rowsAffected);
        }

        if(customer.getEmail() != null){
            var sql = """
                    UPDATE tbl_customers SET email = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getEmail(), customer.getId());
            System.out.println("Update customer email result =  " + rowsAffected);
        }

        if(customer.getMobile() != null){
            var sql = """
                    UPDATE tbl_customers SET mobile = ?
                    WHERE id = ?
                    """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getMobile(), customer.getId());
            System.out.println("Update customer mobile result =  " + rowsAffected);
        }

    }
}
