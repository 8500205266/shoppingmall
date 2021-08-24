package com.shoppingmall.itemUtil;
import com.shoppingmall.model.ItemsResponse;
import com.shoppingmall.utils.ItemUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RunWith(SpringRunner.class)
public class ItemUtilTest
{
    @InjectMocks
    public ItemUtil itemUtilInjectMock;

    private static final Logger logger = LoggerFactory.getLogger(ItemUtilTest.class);
    @BeforeEach
    void init()
    {
        ItemsResponse itemsResponse=new ItemsResponse();
        //List<ItemsResponse> sortedList=new ArrayList<>();
    }
    @Test
    public void freeOfferTypePriceTest()
    {
        List<ItemsResponse> sortedList=new ArrayList<>();
        ItemsResponse itemsResponse=new ItemsResponse();
        sortedList.add(new ItemsResponse(1,10,5,1,"free-offer",1));
        sortedList.add(new ItemsResponse(2,20,10,1,"free-offer",2));
        sortedList.add(new ItemsResponse(3,30,20,1,"free-offer",2));
       // final int freeOfferTypeItemsPrice = itemUtilInjectMock.freeOfferItemsPrice(sortedList);
       logger.info("freeOfferTypeItemsPrice-->{}",itemUtilInjectMock.freeOfferItemsPrice(sortedList));
        Assert.assertEquals(50,itemUtilInjectMock.freeOfferItemsPrice(sortedList));
    }
    @Test
    public void dollerOffTypePriceTest()
    {
        List<ItemsResponse> sortedList=new ArrayList<>();
        ItemsResponse itemsResponse=new ItemsResponse();
        sortedList.add(new ItemsResponse(1,20,10,1,"doller-off",1));
        sortedList.add(new ItemsResponse(2,30,20,1,"doller-off",2));
        sortedList.add(new ItemsResponse(3,10,5,1,"doller-off",3));
        final int dollerOffTypeItemsPrice  = itemUtilInjectMock.dolllerOffPrrice(sortedList);
        logger.info("dollerOffTypeItemsPrice--{}",dollerOffTypeItemsPrice);
        Assert.assertEquals(50,dollerOffTypeItemsPrice);
    }

    @Test
    public void finalFreeOfferListMethodTest()
    {
        HashMap<Integer, List<ItemsResponse>> freeOfferItemsMap = new HashMap<Integer, List<ItemsResponse>>();
        List<ItemsResponse> freeOfferItemResponses=new ArrayList<>();
        freeOfferItemResponses.add(new ItemsResponse(1,50,5,1,"offer-type",1));
        freeOfferItemResponses.add(new ItemsResponse(1,50,5,1,"offer-type",1));
        freeOfferItemsMap.put(1,freeOfferItemResponses);
        final List<ItemsResponse> finaFreeOfferListMethod = itemUtilInjectMock.finalFreeOfferList(freeOfferItemsMap, freeOfferItemResponses);
        logger.info("finaFreeOfferListMethod-->{}",finaFreeOfferListMethod );
        Assert.assertNotNull(finaFreeOfferListMethod);

    }
    @Test
    public void finalDollerOffListMethodTest()
    {
        HashMap<Integer, List<ItemsResponse>> dolleritemsMap = new HashMap<Integer, List<ItemsResponse>>();
        List<ItemsResponse> dollerOffItemResponse=new ArrayList<>();
        dollerOffItemResponse.add(new ItemsResponse(1,5,3,4,"doller-off",1));
        dolleritemsMap.put(1,dollerOffItemResponse);
        final List<ItemsResponse> finalDollerOffListMethod = itemUtilInjectMock.finalDollerOffList(dolleritemsMap,dollerOffItemResponse);
        logger.info("finalDollerOffListMethod-->{}",finalDollerOffListMethod);
        Assert.assertNotNull(finalDollerOffListMethod);
    }

}
