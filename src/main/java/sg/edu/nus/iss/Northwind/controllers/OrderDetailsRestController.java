package sg.edu.nus.iss.Northwind.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Northwind.models.OrderDetails;
import sg.edu.nus.iss.Northwind.services.OrderDetailsService;

@RestController
@RequestMapping("/order")
public class OrderDetailsRestController {
    
    @Autowired
    private OrderDetailsService odSvc;

    @GetMapping("{orderId}")
    public ResponseEntity<String> getOrderDetails(@PathVariable Integer orderId) {
        OrderDetails orderDetails = odSvc.getOrderDetails(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderDetails.toJSON().toString());
    }
}
