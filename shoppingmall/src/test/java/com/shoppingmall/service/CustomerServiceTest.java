package com.shoppingmall.service;


import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
 public class CustomerServiceTest
{
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    static Customer customer1;
    static  Customer customer2;
    static CustomerDto customerDto;
    static Customer customer;
    static List<Customer> customerList;
    UtilityModel utilityModel=new UtilityModel();

    @Before
     public void init()
    {
        customer1=utilityModel.customerData().get(0);
        customer2=utilityModel.customerData().get(1);
        customer=utilityModel.customerData().get(2);
        customerDto=utilityModel.customerDtoData();
        customerList=utilityModel.customerData();
        logger.info("customer list--{}"+customerList);
    }

    @Test
    public void getCustomersTest()
    {
        when(customerRepository.findAll()).thenReturn(customerList);
        logger.info("Customer List-->"+customerService.getCustomer().toString());
        final List<Customer> actualResult = customerService.getCustomer();
        Assert.assertNotNull(actualResult);
    }

    @Test
    public void getCustomerByIdTest()
    {
         Integer customerId=2;
       when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer1));
       Assert.assertEquals(1,customerService.findCustomerById(customerId).stream().count());
    }

    @Test
     public void saveCustomerTest()
    {
        when(customerRepository.save(customer)).thenReturn(customer);
        Assert.assertEquals(customer,customerService.addCustomer(customer));
    }

    @Test
    public void deleteCustomerTest()
    {
        customerService.deleteCustomer(customer);
        verify(customerRepository,times(1)).delete(customer);
    }
    @Test
   public void updateCustomerTest()
    {
        when(customerRepository.save(customer)).thenReturn(customer);
        Assert.assertNotNull(customerService.updateCustomerData(customer));
    }
}
