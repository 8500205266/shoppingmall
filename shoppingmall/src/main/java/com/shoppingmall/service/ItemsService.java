package com.shoppingmall.service;

import com.shoppingmall.model.Items;
import com.shoppingmall.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService
{
    @Autowired
    private ItemsRepository itemsRepository;

    public List<Items> getItems()
    {
        return itemsRepository.findAll();
    }

    public Items addItems(Items items)
    {
        return itemsRepository.save(items);
    }
}
