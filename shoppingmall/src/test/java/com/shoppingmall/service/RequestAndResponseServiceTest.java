package com.shoppingmall.service;

import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.utils.ItemUtil;
import com.shoppingmall.model.*;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class RequestAndResponseServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ItemsRepository itemsRepository;
    @Mock
    ItemUtil itemUtil;
    @Mock
    OffersRepository offersRepository;

    @InjectMocks
    RequestAndResponseService requestAndResponseService;

    @Test
    public void test_should_placeOrder() throws CustomerNotFoundException
    {
        when(customerRepository.findById(any())).thenReturn(Optional.of
                (new Customer(1, "prashanth")));

        when(itemUtil.freeOfferMethod(any(), any())).thenReturn
                (new ItemsResponse(1, 10, 10, 2, "free-offer", 1));
        when(itemUtil.dollerOffMethod(any(), any())).thenReturn
                (new ItemsResponse(2, 20, 5, 2, "doller-off", 2));
        when(itemsRepository.findById(any())).
                thenReturn(Optional.of(new Items(1, 10, 1)),
                        Optional.of(new Items(2, 20, 2)));
        when(offersRepository.findById(any())).thenReturn(Optional.of
                (new Offers(1, "free-offer", 10)),(Optional.of
                        (new Offers(1, "doller-off", 5))));

        when(itemUtil.freeOfferItemsPrice(any())).thenReturn(10);
        when(itemUtil.dolllerOffPrrice(any())).thenReturn(35);

        final ResponseCustomerItems responseCustomerItems = requestAndResponseService
                .placeOrder(new RequestCustomerItems(1, Collections.singletonList(10)));

        Assert.assertEquals("prashanth", responseCustomerItems.getCustomerName());
        Assert.assertEquals("10",responseCustomerItems.getFreeItemsPrice().toString());
        Assert.assertEquals("35",responseCustomerItems.getDollerItemsPrice().toString());
        Assert.assertEquals("45",responseCustomerItems.getTotalPrice().toString());
        //Assert.assertEquals("free-offer",responseCustomerItems.getOffertype().);
    }
}