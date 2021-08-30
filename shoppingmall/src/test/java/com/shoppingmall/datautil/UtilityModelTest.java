package com.shoppingmall.datautil;

import com.shoppingmall.controller.CustomerControllerTest;
import com.shoppingmall.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
public class UtilityModelTest
{
    @InjectMocks
    public UtilityModel utilityModel;

    @Test
    public void  utilityModelTest()
    {
        final List<Offers> offersList = utilityModel.offersData();
        final List<Customer> customerList = utilityModel.customerData();
        final List<Items> itemList = utilityModel.itemsData();
        final OfferDto offersDto = utilityModel.offerDtoData();
        final CustomerDto customerDto = utilityModel.customerDtoData();
        final ItemsDto itemsDto = utilityModel.itemsDtoData();
        Assert.assertNotNull(offersList);
        Assert.assertNotNull(customerList);
        Assert.assertNotNull(itemList);
        Assert.assertNotNull(offersDto);
        Assert.assertNotNull(itemsDto);
        Assert.assertNotNull(customerDto);
    }
    @Test
    public void itemDataGroupByOfferTypeTest()
    {
        final HashMap<String,List<ItemsResponse>> itemDataGroupByOfferType=utilityModel.itemDataGroupByOfferType();
        Assert.assertNotNull(itemDataGroupByOfferType);
    }
}

