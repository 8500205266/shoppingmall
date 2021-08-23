package com.shoppingmall.controller;

import com.shoppingmall.exception.ItemsNotFound;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

    @Test
    public void getCustomerListTest()
    {

        when(customerService.getCustomer()).thenReturn(Stream.of(new Customer(1,"Virat"),new Customer(2,"Dhoni")).collect(Collectors.toList()));
        logger.info("Customer Name-->{}",customerController.getCustomer().get(0).getCustomerName());
        Assert.assertEquals("Virat",customerController.getCustomer().get(0).getCustomerName());

    }
    @Test
    public void getCustomerByCustomerIdTest() throws ItemsNotFound {
       final Integer  customerId=1;
        when(customerService.findCustomerById(customerId)).
                thenReturn(Optional.of(new Customer(1, "ravi")), Optional.of(new Customer(2,"rajesh")));
        logger.debug("Customer Name By CustomerId-->{}",customerController.getCustomerById(customerId).getCustomerName());
        Assert.assertEquals("rajesh",customerController.getCustomerById(customerId).getCustomerName());
    }


    @Test
    public void saveCustomerInControllerTest()
    {
        CustomerDto customerDto=new CustomerDto(18,"Kohli");
        Customer customer=new Customer(18,"Kohli");
        when(shoppingMallMapper.toCustomer(customerDto)).thenReturn(customer);
        when(customerService.addCustomer(customer)).thenReturn(customer);
        logger.debug("new Customer--{}"+customer);
        Assert.assertEquals(customer,customerController.saveCustomer(customerDto));
    }

    @Test
    public  void deleteCustomerTest() throws ItemsNotFound {
        Customer customer=new Customer(2,"Kohli");
        when(customerService.findCustomerById(2)).thenReturn(Optional.of(customer));
        customerController.deleteCustomerById(2);
        System.out.println("customer-->"+customer);
        logger.debug("Delete Custommer--{}",customer);
        verify(customerService,times(1)).deleteCustomer(customer);
    }

    @Test
    public void updateCustomerTest() throws ItemsNotFound {

        when(customerService.findCustomerById(20)).
                thenReturn(Optional.of(new Customer(20, "NTR")));
        Customer customer=new Customer(20,"Ram");
        CustomerDto customerDto=new CustomerDto(20,"Ravi");
        when(shoppingMallMapper.toCustomer(customerDto)).thenReturn(customer);
        when(customerService.updateCustomerData(customer)).thenReturn(customer);
        final Customer updateCustomer = customerController.updateData(20, customerDto);
        logger.debug("Update Customer--{}",updateCustomer);
        Assert.assertNotNull(updateCustomer);
    }

}
