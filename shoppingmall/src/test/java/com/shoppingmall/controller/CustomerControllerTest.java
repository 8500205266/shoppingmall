package com.shoppingmall.controller;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerControllerTest
{
    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private ShoppingMallMapper shoppingMallMapper;

   static Customer customer1;
   static  Customer customer2;
   static  CustomerDto customerDto;
   static Customer customer;

    UtilityModel utilityModel=new UtilityModel();
    @Before
    public void init()
    {
        customer1=utilityModel.customerData().get(0);
        customer2=utilityModel.customerData().get(1);
        customer=utilityModel.customerData().get(2);
        customerDto=utilityModel.customerDtoData();
    }

    final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

    @Test
    public void getCustomerListTest()
    {

        when(customerService.getCustomer()).thenReturn(Stream.of(customer1,customer2).collect(Collectors.toList()));
        logger.info("Customer Name-->{}",customerController.getCustomer().get(0).getCustomerName());
        Assert.assertEquals("Virat Kohli",customerController.getCustomer().get(0).getCustomerName());

    }
    @Test
    public void getCustomerByCustomerIdTest() throws CustomerNotFoundException {
       final Integer  customerId=1;
        when(customerService.findCustomerById(customerId)).thenReturn(Optional.of(customer1));
        logger.debug("Customer Name By CustomerId-->{}",customerController.getCustomerById(customerId).getCustomerName());
        Assert.assertEquals("Virat Kohli",customerController.getCustomerById(customerId).getCustomerName());
    }


    @Test
    public void saveCustomerInControllerTest()
    {
        when(shoppingMallMapper.toCustomer(customerDto)).thenReturn(customer);
        when(customerService.addCustomer(customer)).thenReturn(customer);
        logger.debug("new Customer--{}"+customer);
        Assert.assertEquals(customer,customerController.saveCustomer(customerDto));
    }

    @Test
    public  void deleteCustomerTest() throws CustomerNotFoundException
    {
        final Integer customerId=2;
        when(customerService.findCustomerById(customerId)).thenReturn(Optional.of(customer));
        customerController.deleteCustomerById(customerId);
        System.out.println("customer-->"+customer);
        logger.debug("Delete Custommer--{}",customer);
        verify(customerService,times(1)).deleteCustomer(customer);
    }

    @Test
    public void updateCustomerTest() throws CustomerNotFoundException {
        final Integer customerId=1;
        when(customerService.findCustomerById(customerId)).thenReturn(Optional.of(customer));
        when(shoppingMallMapper.toCustomer(customerDto)).thenReturn(customer);
        when(customerService.updateCustomerData(customer)).thenReturn(customer);
        final Customer updateCustomer = customerController.updateData(customerId, customerDto);
        logger.debug("Update Customer--{}",updateCustomer);
        Assert.assertNotNull(updateCustomer);
    }

}
