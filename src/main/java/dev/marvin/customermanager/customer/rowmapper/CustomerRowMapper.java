package dev.marvin.customermanager.customer.rowmapper;

import dev.marvin.customermanager.customer.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        // returns a customer by grabbing the contents of each row
       return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("mobile"));
    }
}
