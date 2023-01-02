package sg.edu.nus.iss.Northwind.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.Northwind.models.OrderDetails;
import sg.edu.nus.iss.Northwind.services.OrderDetailsService;

@Controller
@RequestMapping(path = "/order-details")
public class OrderDetailsController {
    
    @Autowired
    private OrderDetailsService odSvc;

    @PostMapping
    public String getOrderDetails(@RequestBody MultiValueMap<String,String> form, Model model) {
        String orderId = form.getFirst("order-id");
        OrderDetails orderDetails = odSvc.getOrderDetails(Integer.parseInt(orderId));
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }
}
