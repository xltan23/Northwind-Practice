package sg.edu.nus.iss.Northwind.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    
    private Integer orderId; 
    private Integer customerId;
    private DateTime orderDate;
    private Double totalPrice;
    private Double discountedPrice;
    private Double costPrice;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Double getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static OrderDetails create(SqlRowSet srs) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(srs.getInt("order_id"));
        orderDetails.setCustomerId(srs.getInt("customer_id"));
        orderDetails.setOrderDate(new DateTime(
            DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(srs.getString("order_date"))
        ));
        orderDetails.setTotalPrice(srs.getDouble("total_price"));
        orderDetails.setDiscountedPrice(srs.getDouble("discounted_price"));
        orderDetails.setCostPrice(srs.getDouble("cost_price"));
        return orderDetails;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("order_id", orderId)
                    .add("customer_id", customerId)
                    .add("order_date", orderDate.toString())
                    .add("total_price", totalPrice)
                    .add("discounted_price", discountedPrice)
                    .add("cost_price", costPrice)
                    .build();
    }

}
