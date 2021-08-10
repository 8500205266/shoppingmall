package com.shoppingmall.controller;

import com.shoppingmall.model.Items;
import com.shoppingmall.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController
{
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/getitems")
    public List<Items> getItems()
    {
        return itemsService.getItems();
    }

    @PostMapping("/additem")
    public Items addItems(@RequestBody final Items items)
    {
        return itemsService.addItems(items);
    }
}
