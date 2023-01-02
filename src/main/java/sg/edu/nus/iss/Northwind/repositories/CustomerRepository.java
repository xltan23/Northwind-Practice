package sg.edu.nus.iss.Northwind.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Northwind.models.Customer;
import sg.edu.nus.iss.Northwind.models.Order;

import static sg.edu.nus.iss.Northwind.repositories.Queries.*;

@Repository
public class CustomerRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getCustomers(Integer limit, Integer offset) {
        List<Customer> customers = new LinkedList<>();
        if (limit == null) {
            limit = 5;
        }
        if (offset == null) {
            offset = 0;
        }
        final SqlRowSet srs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMERS_WITH_LIMIT_OFFSET, limit, offset);

        while (srs.next()) {
            customers.add(Customer.create(srs));
        }
        return customers;
    }

    public Customer findCustomerById(Integer id) {
        List<Customer> customers = new LinkedList<>();
        final SqlRowSet srs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMERS_BY_ID, id);
        while (srs.next()) {
            customers.add(Customer.create(srs));
        }
        return customers.get(0);
    }

    public List<Order> getCustomersOrder(Integer id) {
        List<Order> orders = new LinkedList<>();
        final SqlRowSet srs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMERS_ORDERS_BY_ID, id);
        while (srs.next()) {
            orders.add(Order.create(srs));
        }
        return orders;
    }
}
