package sg.edu.nus.iss.Northwind.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Northwind.models.OrderDetails;

import static sg.edu.nus.iss.Northwind.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderDetailsRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderDetails getOrderDetails(Integer orderId) {
        List<OrderDetails> orderDetailsList = new LinkedList<>();
        final SqlRowSet srs = jdbcTemplate.queryForRowSet(SQL_SELECT_ORDER_DETAILS, orderId);
        while(srs.next()) {
            orderDetailsList.add(OrderDetails.create(srs));
        }
        return orderDetailsList.get(0);
    }
}
