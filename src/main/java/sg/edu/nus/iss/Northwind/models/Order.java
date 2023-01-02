package sg.edu.nus.iss.Northwind.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {

    // Orders members
    private Integer id;
    private Customer customer;
    private DateTime orderDate;
    private DateTime shipDate;
    private String shipName;
    private Double shippingFee;
    

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public DateTime getShipDate() {
        return shipDate;
    }
    public void setShipDate(DateTime shipDate) {
        this.shipDate = shipDate;
    }
    public String getShipName() {
        return shipName;
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    public Double getShippingFee() {
        return shippingFee;
    }
    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Creation of Order is done under query for Order Table Left Join Customer Table
    public static Order create(SqlRowSet srs) {
        Customer customer = new Customer();
        customer.setId(srs.getInt("customer_id"));
        customer.setCompany(srs.getString("company"));
        customer.setFirstName(srs.getString("first_name"));
        customer.setLastName(srs.getString("last_name"));
        Order order = new Order();
        order.setId(srs.getInt("id"));
        order.setCustomer(customer);
        order.setShipName(srs.getString("ship_name"));
        order.setShippingFee(srs.getDouble("shipping_fee"));
        if (srs.getString("shipped_date") != null) {
            order.setShipDate(new DateTime(
                DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(srs.getString("shipped_date"))
            ));
        }

        order.setOrderDate(new DateTime(
            DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(srs.getString("order_date"))
        ));
        return order;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("order_id", id)
                    .add("order_date", orderDate != null ? orderDate.toString() : "")
                    .add("shipped_date", shipDate != null ? shipDate.toString() : "")
                    .add("ship_name", shipName)
                    .add("shipping_fee", shippingFee)
                    .add("customer_id", customer.getId().toString())
                    .build();
    }
}
