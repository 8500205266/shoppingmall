package com.shoppingmall.service;

import com.shoppingmall.model.Items;
import com.shoppingmall.repository.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 *it has business logic to store, retrieve, delete and updates the ItemsData
 */
public class ItemsService
{
    static final Logger log = LoggerFactory.getLogger(ItemsService.class);
    @Autowired
    private ItemsRepository itemsRepository;

    /**
     *it is used to get list of items
     * @return list of items
     */
    public List<Items> getItems()
    {
        log.debug("List of items in item service{}",itemsRepository.findAll());
        return itemsRepository.findAll();
    }

    /**
     *It is used to save item data
     * @param item which was sent from client
     * @return is used to return the saved item
     */
    public Items addItems(Items item)
    {
        log.debug("this is new item-->{}",item);
        return itemsRepository.save(item);
    }
    /**
     *it is use to send the item data  by using corresponding itemId
     * @param id which was given by client
     * @return item
     */
    public Optional<Items> findItemById(int id)
    {
        log.debug("Item data by Id-->{}",itemsRepository.findById(id));
        return itemsRepository.findById(id);
    }

    /**
     *this used for delete the item
     * @param itemByID this itemById data getting by using corresponding item Id
     */
    public void deleteItem(Items itemByID)
    {
        log.debug("delete item--->{}",itemByID);
        itemsRepository.delete(itemByID);
    }

    /**
     *this is used for update the item
     * @param itemByID this item data getting by using corresponding item Id
     * @return upadte item data
     */
    public Items updateItem(Items itemByID)
    {
        log.debug("update Item--->{}",itemByID);
        return itemsRepository.save(itemByID);
    }
}
