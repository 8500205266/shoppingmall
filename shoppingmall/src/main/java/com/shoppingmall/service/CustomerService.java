package com.shoppingmall.service;

import com.shoppingmall.model.Customer;
import com.shoppingmall.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomer()
    {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }
}
