package com.shoppingmall.controller;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.service.CustomerService;
import com.shoppingmall.service.RequestAndResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController
{


    @Autowired
    private ShoppingMallMapper shoppingMallMapper;

    @Autowired
    public CustomerService customerService;
    static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    String customernotfound="Customer ID is Not Found!!!";
    /**
     *
     * @return it is returns all customers
     */
    @GetMapping()
    public List<Customer> getCustomer()
    {
        return customerService.getCustomer();
    }

    /**
     *it is used to save the customer data
     * @param customerDto
     * @return use to return customer
     */
    @PostMapping()
    public Customer saveCustomer(@RequestBody CustomerDto customerDto)
    {

        Customer customer=shoppingMallMapper.toCustomer(customerDto);
        log.info("customer{}",customer);
        return customerService.addCustomer(shoppingMallMapper.toCustomer(customerDto));
    }


    /**
     * It is provides customer details when we pass customerId
     * @param id pass the customerId
     * @return customer details
     * @throws CustomerNotFoundException if id is not Found then it throws CustomerNotFoundException
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException
    {
        return customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
    }
    /**
     *It is used to delete customer when we pass customerId of customer
     * @param id pass the customer id
     * @throws CustomerNotFoundException if id is not Found then it throws CustomerNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException
    {
        final Customer customerById = customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
          customerService.deleteCustomer(customerById);
    }

    /**
     *It is used to update customer when we pass customerId of customer
     * @param id pass the customer id
     * @param customerDto we will pass customer to customerDto
     * @return it is return updated customer
     * @throws CustomerNotFoundException if customer id not found then it returns CustomerNotFoundException
     */
    @PutMapping("/{id}")
    public Customer updateData(@PathVariable("id") int id, @RequestBody CustomerDto customerDto)
            throws CustomerNotFoundException
        {

        final Customer customerById =customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
            Customer customer=shoppingMallMapper.toCustomer(customerDto);
        customerById.setCustomerName(customer.getCustomerName());
        return customerService.updateCustomerData(customerById);
    }
}
