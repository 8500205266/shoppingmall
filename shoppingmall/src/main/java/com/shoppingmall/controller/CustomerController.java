package com.shoppingmall.controller;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public CustomerService customerService;
    String customernotfound="Customer ID is Not Found!!!";
    /**
     *
     * @return
     */
    @GetMapping()
    public List<Customer> getCustomer()
    {
        return customerService.getCustomer();
    }

    /**
     *
     * @param customerDto
     * @return
     */
    @PostMapping()
    public Customer saveCustomer(@RequestBody CustomerDto customerDto)
    {
        Customer customer=modelMapper.map(customerDto,Customer.class);
        return customerService.addCustomer(customer);
    }


    /**
     *
     * @param id
     * @return
     * @throws CustomerNotFoundException
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException
    {
        return customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
    }
    /**
     *
     * @param id
     * @throws CustomerNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException
    {
        final Customer customerById = customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
          customerService.deleteCustomer(customerById);
    }

    /**
     *
     * @param id
     * @param customerDto
     * @return
     * @throws CustomerNotFoundException
     */
    @PutMapping("/{id}")
    public Customer updateData(@PathVariable("id") int id, @RequestBody CustomerDto customerDto) throws CustomerNotFoundException
    {
        Customer customer=modelMapper.map(customerDto,Customer.class);
        final Customer customerById =customerService.findCustomerById(id).orElseThrow(() -> new
                CustomerNotFoundException(customernotfound));
        customerById.setCustomerName(customer.getCustomerName());
        return customerService.updateCustomerData(customerById);
    }
}
