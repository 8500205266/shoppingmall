package com.shoppingmall.controller;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.exception.ItemNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsDto;
import com.shoppingmall.service.ItemsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ItemsControllerTest
{
    @InjectMocks
    private ItemsController itemsController;

    @Mock
    private ItemsService itemsService;

    @Mock
    private ShoppingMallMapper shoppingMallMapper;

    final Logger logger = LoggerFactory.getLogger(ItemsControllerTest.class);

    static Items item1;
    static Items item2;
    static Items item;
    static ItemsDto itemsDto;
    UtilityModel utilityModel=new UtilityModel();
    static List<Items> itemsList;

    @Before
    public void init()
    {
        item1=utilityModel.itemsData().get(0);
        item2=utilityModel.itemsData().get(1);
        logger.info("item1--->"+item1);
        itemsList =utilityModel.itemsData();

    }

    @Test
    public void getItemsListTest()
    {
        when(itemsService.getItems()).thenReturn(itemsList);
        logger.info("Items -->{}",itemsController.getItems());
        Assert.assertNotNull(itemsController.getItems());

    }
    @Test
    public void getItemsByItemIdTest() throws ItemNotFoundException
    {
        final Integer itemId=1;
        when(itemsService.findItemById(itemId)).thenReturn(Optional.of(item1));
        logger.debug("Items By ItemId-->{}",itemsController.getitemById(itemId));
        Assert.assertNotNull(itemsController.getitemById(itemId));
    }

    @Test
    public void saveItemsInItemsControllerTest()
    {
      /* ItemsDto itemsDto=new ItemsDto(1,10,1);
       Items items=new Items(1,10,1);*/
        when(shoppingMallMapper.toItems(itemsDto)).thenReturn(item1);
        when(itemsService.addItems(item1)).thenReturn(item1);
        logger.debug("new item--{}"+item1);
        Assert.assertEquals(item1,itemsController.addItems(itemsDto));
    }

    @Test
    public  void deleteItemsInItemsController() throws ItemNotFoundException
    {
        final Integer itemId=1;
        when(itemsService.findItemById(itemId)).thenReturn(Optional.of(item1));
        itemsController.deleteItemById(itemId);
        logger.debug("Delete item--{}",item1);
        verify(itemsService,times(1)).deleteItem(item1);
    }

    @Test
    public void updateItemInItemControllerTest() throws ItemNotFoundException {

        final Integer itemId=1;
        when(itemsService.findItemById(itemId)).thenReturn(Optional.of(item1));
        when(shoppingMallMapper.toItems(itemsDto)).thenReturn(item1);
        when(itemsService.updateItem(item1)).thenReturn(item1);
        final Items updateItem = itemsController.updateData(itemId,itemsDto);
        logger.debug("Update Items--{}",updateItem);
        Assert.assertNotNull(updateItem);
    }

}
