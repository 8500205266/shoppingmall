package com.shoppingmall.service;

import com.shoppingmall.model.Items;
import com.shoppingmall.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService
{
    @Autowired
    private ItemsRepository itemsRepository;

    /**
     *
     * @return
     */
    public List<Items> getItems()
    {
        return itemsRepository.findAll();
    }

    /**
     *
     * @param items
     * @return
     */
    public Items addItems(Items items)
    {
        return itemsRepository.save(items);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Items> findItemById(int id)
    {
        return itemsRepository.findById(id);
    }

    /**
     *
     * @param itemByID
     */
    public void deleteItem(Items itemByID)
    {
        itemsRepository.delete(itemByID);
    }

    /**
     *
     * @param itemByID
     * @return
     */
    public Items updateItem(Items itemByID)
    {
        return itemsRepository.save(itemByID);
    }
}
