package com.shoppingmall.controller;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.model.ItemsResponse;
import com.shoppingmall.model.RequestCustomerItems;
import com.shoppingmall.model.ResponseCustomerItems;
import com.shoppingmall.service.RequestAndResponseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RequestCustomerItemsControllerTest
{
    @Mock
    RequestAndResponseService requestAndResponseService;

    @InjectMocks
    RequestCustomerItemsController requestCustomerItemsController;

    final Logger logger = LoggerFactory.getLogger(RequestCustomerItemsControllerTest.class);

    UtilityModel utilityModel=new UtilityModel();

    static HashMap<String,List<ItemsResponse>> itemDataGroupByOfferTypeMap;

    @Before
    public void init()
    {
        final HashMap<String, List<ItemsResponse>> itemDataGroupByOfferTypeMap = utilityModel.itemDataGroupByOfferType();
       logger.info("itemDataGroupByOfferTypeMap--{}",itemDataGroupByOfferTypeMap);

    }
    @Test
    public void placeOrderAndRespnseOrderTestInController() throws CustomerNotFoundException
    {
        RequestCustomerItems requestCustomerItems=new RequestCustomerItems(1,List.of(1,2));
        ResponseCustomerItems responseCustomerItems=new ResponseCustomerItems("prashanth",1,itemDataGroupByOfferTypeMap,100,20,30);
        when(requestAndResponseService.placeOrder(requestCustomerItems)).thenReturn(responseCustomerItems);
        final ResponseCustomerItems response = requestCustomerItemsController.addCustomerItemsList(requestCustomerItems);
        logger.debug("response-{}"+response.toString());
        Assert.assertNotNull(response);

    }

}
