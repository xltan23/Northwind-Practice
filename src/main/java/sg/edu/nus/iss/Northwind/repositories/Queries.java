package sg.edu.nus.iss.Northwind.repositories;

public class Queries {
    
    public static final String SQL_SELECT_CUSTOMERS_WITH_LIMIT_OFFSET = "SELECT id, company, last_name, first_name FROM customers LIMIT ? OFFSET ?"; 

    public static final String SQL_SELECT_CUSTOMERS_BY_ID = "SELECT id, company, last_name, first_name FROM customers WHERE id = ?";

    public static final String SQL_SELECT_CUSTOMERS_ORDERS_BY_ID = "SELECT c.id as customer_id, c.company, c.last_name, c.first_name, o.id as order_id, DATE_FORMAT(o.order_date, \"%d/%m/%y\") as order_date, DATE_FORMAT(o.shipped_date, \"%d/%m/%y\") as shipped_date, o.ship_name, o.shipping_fee FROM customers c, orders o WHERE c.id = o.customer_id and o.customer_id = ?";

    public static final String SQL_SELECT_ORDER_DETAILS = """
        SELECT 
        o.id as order_id,
        o.customer_id as customer_id,
        DATE_FORMAT(o.order_date, \"%d/%m/%y\") as order_date,
        sum(od.quantity * od.unit_price) as total_price,
        sum(od.quantity * od.unit_price * od.discount) as discount,
        sum(od.quantity * od.unit_price) - sum(od.quantity * od.unit_price * od.discount) as discounted_price,
        sum(od.quantity * p.standard_cost) as cost_price
        FROM orders o
        LEFT JOIN order_details od
        ON o.id = od.order_id
        LEFT JOIN products p
        ON od.product_id = p.id
        WHERE o.id = ?;
            """;
}
