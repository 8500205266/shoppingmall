package com.shoppingmall.service;

import com.shoppingmall.model.Customer;
import com.shoppingmall.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
 class CustomerServiceTest
{
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
     void getCustomersTest()
    {
        final OngoingStubbing<List<Customer>> customerList = when(customerRepository.findAll())
                .thenReturn(Stream.of(new Customer(1, "venky"),
                new Customer(2, "raaju")).collect(Collectors.toList()));
        logger.info("Customer List-->"+customerService.getCustomer().toString());

        final List<Customer> actualResult = customerService.getCustomer();

        Assert.assertEquals(2, actualResult.stream().count());
    }

    @Test
    void getCustomerByIdTest()
    {
         Integer customerId=2;
       when(customerRepository.findById(customerId)).
               thenReturn(Optional.of(new Customer(1, "ravi")),
                       Optional.of(new Customer(2,"Rajesh")));
       Assert.assertEquals(1,customerService.findCustomerById(customerId).stream().count());
    }

    @Test
     void saveCustomerTest()
    {
        Customer customer=new Customer(18,"Kohli");
        when(customerRepository.save(customer)).thenReturn(customer);
        Assert.assertEquals(customer,customerService.addCustomer(customer));
    }

    @Test
    void deleteCustomerTest()
    {
        Integer customerId=1;
        Customer customer=new Customer(18,"Kohli");
        customerService.deleteCustomer(customer);
        verify(customerRepository,times(1)).delete(customer);
    }
}
