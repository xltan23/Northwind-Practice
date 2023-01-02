package sg.edu.nus.iss.Northwind.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Northwind.models.Customer;
import sg.edu.nus.iss.Northwind.models.Order;
import sg.edu.nus.iss.Northwind.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getCustomers(Integer limit, Integer offset) {
        return customerRepo.getCustomers(limit, offset);
    }

    public Customer findCustomerById(Integer id) {
        return customerRepo.findCustomerById(id);
    }

    public List<Order> getCustomerOrders(Integer id) {
        return customerRepo.getCustomersOrder(id);
    }
}
