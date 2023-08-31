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
                FROM tbl_customers
                """;
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        String sql = """
                SELECT id, name, email, mobile
                FROM tbl_customers
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, customerRowMapper, customerId).stream().findFirst();
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
    public void updateCustomer(Customer customer){
        if (customer.getName() != null && !customer.getName().isEmpty()) {
        String sql = """
                UPDATE tbl_customers
                SET name = ?
                WHERE id = ?
                """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getName(), customer.getId());
            System.out.println("JDBC UPDATE customer name result = " + rowsAffected);
        }

        if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
            String sql = """
                UPDATE tbl_customers
                SET email = ?
                WHERE id = ?
                """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getEmail(), customer.getId());
            System.out.println("JDBC UPDATE customer email result = " + rowsAffected);
        }

        if (customer.getMobile() != null && !customer.getMobile().isEmpty()) {
            String sql = """
                UPDATE tbl_customers
                SET mobile = ?
                WHERE id = ?
                """;
            int rowsAffected = jdbcTemplate.update(sql, customer.getMobile(), customer.getId());
            System.out.println("JDBC UPDATE customer age result = " + rowsAffected);
        }

    }

    @Override
    public void deleteCustomerById(Long customerId) {
        String sql = """
                DELETE  FROM tbl_customers
                WHERE id = ?
                """;
        int rowsAffected = jdbcTemplate.update(sql, customerId);
        System.out.println("JDBC DELETE result = " + rowsAffected);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {

        String sql = """
                SELECT COUNT(*)
                FROM tbl_customers
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
