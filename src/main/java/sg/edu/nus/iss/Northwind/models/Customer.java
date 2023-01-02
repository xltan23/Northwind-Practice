package sg.edu.nus.iss.Northwind.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {

    // Customers details
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Customer create(SqlRowSet srs) {
        Customer customer = new Customer();
        customer.setId(srs.getInt("id"));
        customer.setFirstName(srs.getString("first_name"));
        customer.setLastName(srs.getString("last_name"));
        customer.setCompany(srs.getString("company"));
        return customer;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("id", id)
                    .add("first_name", firstName)
                    .add("last_name", lastName)
                    .add("company", company)
                    .build();
    }
}
