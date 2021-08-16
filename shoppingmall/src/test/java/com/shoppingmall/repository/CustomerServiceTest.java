package com.shoppingmall.repository;


import com.shoppingmall.utils.ItemUtil;
import com.shoppingmall.model.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
     class CustomerServiceTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ItemUtil itemUtil;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private OffersRepository offersRepository;

    @Test
    void customerNameTest()
    {
        final Optional<Customer> customerId = customerRepository.findById(1);
        Assert.assertEquals("Prashanth", customerId.get().getCustomerName());
        Assert.assertFalse(customerId.get().getCustomerName() == "balu");
    }
}

