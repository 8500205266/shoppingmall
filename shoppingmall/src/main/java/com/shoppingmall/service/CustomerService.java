package com.shoppingmall.service;

import com.shoppingmall.model.Customer;
import com.shoppingmall.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 *it has business logic to store, retrieve, delete and updates the CustomerData
 */
public class CustomerService
{
    //gfdnjkgkhjgf
    //dfgkhfkhgkjgddf
    static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;
    /**
     *it is used to get list of customers
     * @return list of customers
     */
    public List<Customer> getCustomer()
    {
        log.debug("List of customers in customer service{}",customerRepository.findAll());
        return customerRepository.findAll();

    }
    /**
     *It is used to save customer data
     * @param customer which was sent from client
     * @return is used to return the saved customer
     */
    public Customer addCustomer(Customer customer)
    {
        log.debug("this is new customer-->{}",customer);
        return customerRepository.save(customer);
    }
    /**
     *it is use to send the customer data  by using corresponding customer Id
     * @param id which was given by client
     * @return customer
     */
    public Optional<Customer> findCustomerById(int id)
    {
        log.debug("Customer data  by Id-->{}",customerRepository.findById(id));
        return customerRepository.findById(id);
    }
    /**
     *this used for delete the customer
     * @param customerById this customerById data  getting by using corresponding customer Id
     */
    public void deleteCustomer(Customer customerById)
    {
        log.debug("delete Customer--->{}",customerById);
        customerRepository.delete(customerById);
    }
    /**
     *this is used for update the customer
     * @param customerById this customer data getting by using corresponding customer Id
     * @return upadte customer data
     */
    public Customer updateCustomerData(Customer customerById)
    {
        log.debug("update Customer--->{}",customerById);
        return customerRepository.save(customerById);
    }
}
