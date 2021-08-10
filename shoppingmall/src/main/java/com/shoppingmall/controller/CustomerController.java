package com.shoppingmall.controller;

import com.shoppingmall.model.Customer;
import com.shoppingmall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    public CustomerService customerService;

    @RequestMapping("/getcustomer")
    public List<Customer> getCustomer()
    {
        return customerService.getCustomer();
    }

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody final Customer customer)
    {
        return customerService.addCustomer(customer);
    }
}
