package com.shoppingmall;

import com.shoppingmall.model.Customer;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.service.CustomerService;
import com.shoppingmall.service.ItemsService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingmallApplicationTests
{
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
     public void testmainApplication()
    {
        when(customerRepository.findById(any()))
                .thenReturn(Optional.of(new Customer(1, "ravi")),
                        Optional.of(new Customer(2, "ramesh")));
        Assert.assertNotNull(customerService.findCustomerById(1));
    }

}

