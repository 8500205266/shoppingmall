package com.shoppingmall.service;

import com.shoppingmall.model.Customer;
import com.shoppingmall.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;
    /**
     *
     * @return
     */
    public List<Customer> getCustomer()
    {
        return customerRepository.findAll();
    }
    /**
     *
     * @param customer
     * @return
     */
    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }
    /**
     *
     * @param id
     * @return
     */
    public Optional<Customer> findCustomerById(int id)
    {
        return customerRepository.findById(id);
    }
    /**
     *
     * @param customerById
     */
    public void deleteCustomer(Customer customerById)
    {
        customerRepository.delete(customerById);
    }
    /**
     *
     * @param customerById
     * @return
     */
    public Customer updateCustomerData(Customer customerById)
    {
        return customerRepository.save(customerById);
    }
}
