package com.shoppingmall.service;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsDto;
import com.shoppingmall.repository.ItemsRepository;
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
public class ItemsServiceTest
{
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @InjectMocks
    private ItemsService itemsService;

    @Mock
    private ItemsRepository itemsRepository;


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
        itemsList =utilityModel.itemsData();

    }

    @Test
    public void getItemsTest()
    {
        when(itemsRepository.findAll()).thenReturn(itemsList);
        logger.info("Item List-->"+itemsService.getItems().toString());
        Assert.assertNotNull(itemsService.getItems());
    }

    @Test
    public void getItemByIdTest() {
        Integer itemId = 1;
        when(itemsRepository.findById(itemId)).thenReturn(Optional.of(item1));
        Assert.assertEquals(1, itemsService.findItemById(itemId).stream().count());

    }
        @Test
        public void saveItemTest()
        {
            when(itemsRepository.save(item)).thenReturn(item);
            Assert.assertEquals(item,itemsService.addItems(item));
        }

    @Test
   public void deleteItemInItemServiceTest()
    {
        itemsService.deleteItem(item);
        verify(itemsRepository,times(1)).delete(item);
    }
    @Test
    public void updateItemTestInItemServiceTest()
    {
        when(itemsRepository.save(item1)).thenReturn(item1);
        Assert.assertNotNull(itemsService.updateItem(item1));
    }
    }



