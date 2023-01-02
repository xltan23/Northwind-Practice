package sg.edu.nus.iss.Northwind.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.Northwind.models.Customer;
import sg.edu.nus.iss.Northwind.models.Order;
import sg.edu.nus.iss.Northwind.services.CustomerService;

@RestController
@RequestMapping(path = "/customer", produces=MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    
    @Autowired
    private CustomerService customerSvc;

    @GetMapping
    public ResponseEntity<String> getCustomers(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
        List<Customer> customers = customerSvc.getCustomers(limit, offset);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Customer customer : customers) {
            jab.add(customer.toJSON());
        }
        JsonArray ja = jab.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ja.toString());
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId) {
        Customer customer = customerSvc.findCustomerById(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer.toJSON().toString());
    }

    @GetMapping(path = "orders/{customerId}")
    public ResponseEntity<String> getCustomerOrders(@PathVariable Integer customerId) {
        List<Order> orders = customerSvc.getCustomerOrders(customerId);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Order order : orders) {
            jab.add(order.toJSON());
        }
        JsonArray ja = jab.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ja.toString());
    }
}
