package sg.edu.nus.iss.Northwind.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Northwind.models.OrderDetails;
import sg.edu.nus.iss.Northwind.repositories.OrderDetailsRepository;

@Service
public class OrderDetailsService {
    
    @Autowired
    private OrderDetailsRepository odRepo;

    public OrderDetails getOrderDetails(Integer orderId) {
        return odRepo.getOrderDetails(orderId);
    }
}
