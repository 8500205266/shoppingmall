package com.shoppingmall.service;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.utils.ItemUtil;
import com.shoppingmall.model.*;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class RequestAndResponseServiceTest
{
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



    static Customer customer1;
    static  Customer customer2;
    static CustomerDto customerDto;
    static Customer customer;
    static List<Customer> customerList;
    UtilityModel utilityModel=new UtilityModel();

    static Items item1;
    static Items item2;
    static Items item;
    static List<Items> itemsList;

    static Offers offer1;
    static Offers offer2;
    static OfferDto offerDto;
    static List<Offers> offersList;

    @Before
    public void init()
    {
        customer1=utilityModel.customerData().get(0);
        customer2=utilityModel.customerData().get(1);
        customer=utilityModel.customerData().get(2);
        customerDto=utilityModel.customerDtoData();
        customerList=utilityModel.customerData();
        item1=utilityModel.itemsData().get(0);
        item2=utilityModel.itemsData().get(1);
        itemsList =utilityModel.itemsData();
        offer1=utilityModel.offersData().get(0);
        offer2=utilityModel.offersData().get(1);
        offersList =utilityModel.offersData();
    }

    @Test
    public void test_should_placeOrder() throws CustomerNotFoundException
    {
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer1));
        when(itemUtil.freeOfferMethod(any(), any())).thenReturn(new ItemsResponse(1, 10, 10, 2, "free-offer", 1));
        when(itemUtil.dollerOffMethod(any(), any())).thenReturn(new ItemsResponse(2, 20, 5, 2, "doller-off", 2));
        when(itemsRepository.findById(any())).thenReturn(Optional.of(item1));
        when(offersRepository.findById(any())).thenReturn(Optional.of(offer1));
        when(itemUtil.freeOfferItemsPrice(any())).thenReturn(10);
        when(itemUtil.dolllerOffPrrice(any())).thenReturn(35);

        final ResponseCustomerItems responseCustomerItems = requestAndResponseService.placeOrder(new RequestCustomerItems(1, Collections.singletonList(10)));

        Assert.assertEquals("Virat Kohli", responseCustomerItems.getCustomerName());
        Assert.assertEquals("10",responseCustomerItems.getFreeItemsPrice().toString());
        Assert.assertEquals("35",responseCustomerItems.getDollerItemsPrice().toString());
        Assert.assertEquals("45",responseCustomerItems.getTotalPrice().toString());
        Assert.assertNotNull(responseCustomerItems.getCustomerId());
        Assert.assertNotNull(responseCustomerItems.getOffertype());
    }
}