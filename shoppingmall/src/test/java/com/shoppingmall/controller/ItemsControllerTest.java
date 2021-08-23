package com.shoppingmall.controller;

import com.shoppingmall.exception.ItemNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsDto;
import com.shoppingmall.service.ItemsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
public class ItemsControllerTest
{
    @InjectMocks
    private ItemsController itemsController;

    @Mock
    private ItemsService itemsService;

    @Mock
    private ShoppingMallMapper shoppingMallMapper;

    final Logger logger = LoggerFactory.getLogger(ItemsControllerTest.class);

    Items item1;
    Items item2;
    ItemsDto itemsDto;

    @BeforeAll
    public void init()
    {
        Items item1=new Items(1,10,1);
        Items item2=new Items(2,20,2);
        ItemsDto itemDto=new ItemsDto(1,10,1);
    }

    @Test
    public void getItemsListTest()
    {

        when(itemsService.getItems()).thenReturn(Stream.of(new Items(1,10,1),
                new Items(3,30,2)).collect(Collectors.toList()));
        logger.info("Items -->{}",itemsController.getItems());
        Assert.assertNotNull(itemsController.getItems());

    }
    @Test
    public void getItemsByItemIdTest() throws ItemNotFoundException
    {
        final Integer  itemId=1;
        when(itemsService.findItemById(itemId)).
                thenReturn(Optional.of(new Items(1,10,1)),
                        Optional.of(new Items(2,20,2)));
        logger.debug("Items By ItemId-->{}",itemsController.getitemById(itemId));
        Assert.assertNotNull(itemsController.getitemById(itemId));
    }


    @Test
    public void saveItemsInItemsControllerTest()
    {
       ItemsDto itemsDto=new ItemsDto(1,10,1);
       Items items=new Items(1,10,1);
        when(shoppingMallMapper.toItems(itemsDto)).thenReturn(items);
        when(itemsService.addItems(items)).thenReturn(items);
        logger.debug("new item--{}"+items);
        Assert.assertEquals(items,itemsController.addItems(itemsDto));
    }

    @Test
    public  void deleteItemsInItemsController() throws ItemNotFoundException
    {
        final Integer itemId=2;
        Items item=new Items(1,10,1);
        when(itemsService.findItemById(itemId)).thenReturn(Optional.of(item));
        itemsController.deleteItemById(itemId);
        logger.debug("Delete item--{}",item);
        verify(itemsService,times(1)).deleteItem(item);
    }

    @Test
    public void updateItemInItemControllerTest() throws ItemNotFoundException {

        final Integer itemId=1;
        when(itemsService.findItemById(itemId)).
                thenReturn(Optional.of(new Items(1,20, 2)));
        ItemsDto itemsDto=new ItemsDto(1,20,2);
        Items items=new Items(1,10,1);
        when(shoppingMallMapper.toItems(itemsDto)).thenReturn(items);
        when(itemsService.updateItem(items)).thenReturn(items);
        final Items updateItem = itemsController.updateData(itemId,itemsDto);
        logger.debug("Update Items--{}",updateItem);
        Assert.assertNotNull(updateItem);
    }

}
