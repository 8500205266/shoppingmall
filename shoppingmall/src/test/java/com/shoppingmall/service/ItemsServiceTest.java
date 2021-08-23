package com.shoppingmall.service;

import com.shoppingmall.model.Customer;
import com.shoppingmall.model.Items;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
class ItemsServiceTest
{
    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    @Autowired
    private ItemsService itemsService;

    @MockBean
    private ItemsRepository itemsRepository;

    @Test
    void getItemsTest()
    {
        final OngoingStubbing<List<Items>> itemsList = when(itemsRepository.findAll())
                .thenReturn(Stream.of(new Items(10,50,12),
                        new Items(11,40,15)).collect(Collectors.toList()));
        System.out.println("Item List-->"+itemsService.getItems().toString());
        Assert.assertEquals(2,itemsService.getItems().stream().count());
    }

    @Test
    void getItemByIdTest() {
        Integer itemId = 10;
        when(itemsRepository.findById(itemId)).
                thenReturn(Optional.of(new Items(10, 50, 12)),
                        Optional.of(new Items(11, 40, 15)));
        Assert.assertEquals(1, itemsService.findItemById(itemId).stream().count());

    }
        @Test
        void saveItemTest()
        {
            Items item=new Items(10,50,12);
            when(itemsRepository.save(item)).thenReturn(item);
            Assert.assertEquals(item,itemsService.addItems(item));
        }

    @Test
    void deleteItemInItemServiceTest()
    {
        Integer itemId=10;
        Items item=new Items(10,50,12);
        itemsService.deleteItem(item);
        verify(itemsRepository,times(1)).delete(item);
    }
    @Test
    void updateItemTestInItemServiceTest()
    {
        Items item=new Items(10,50,12);
        when(itemsRepository.save(item)).thenReturn(item);
        Assert.assertNotNull(itemsService.updateItem(item));
    }
    }



