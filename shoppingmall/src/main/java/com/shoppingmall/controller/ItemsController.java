package com.shoppingmall.controller;

import com.shoppingmall.exception.ItemNotFoundException;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsDto;
import com.shoppingmall.service.ItemsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController
{
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     *it is use to provide all items
     * @return all items
     */
    @GetMapping()
    public List<Items> getItems()
    {
        return itemsService.getItems();
    }

    /**
     *it is use to save item
     * @param itemsDto it will taking item
     * @return it is return item
     */
    @PostMapping()
    public Items addItems(@RequestBody ItemsDto itemsDto)
    {
        Items items=modelMapper.map(itemsDto,Items.class);
        return itemsService.addItems(items);
    }

    /**
     *It is provides item details when we pass itemId
     * @param id we will pass item id
     * @return it is return the items corresponding that itemId
     * @throws ItemNotFoundException if itemid not there then it throws ItemNotFoundException
     */
    @GetMapping("/{id}")
    public Items getitemById(@PathVariable("id") int id) throws ItemNotFoundException
    {
        return itemsService.findItemById(id).orElseThrow(() -> new
                ItemNotFoundException("Item ID is Not Found!!!"));
    }

    /**
     *It is used to delete item when we pass itemId of item
     * @param id pass the item id
     * @throws ItemNotFoundException if id is not Found then it throws ItemNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable("id") int id) throws ItemNotFoundException
    {
        final Items itemByID = itemsService.findItemById(id).orElseThrow(() -> new
                ItemNotFoundException("Item ID is Not Found!!!"));
        itemsService.deleteItem(itemByID);
    }

    /**
     *It is used to update item when we pass citemId of item
     * @param id pass the item id
     * @param itemsDto we will pass item
     * @return it is return updated item
     * @throws ItemNotFoundException if item not found then it throws custom ItemNotFoundException
     */
    @PutMapping("/{id}")
    public Items updateData(@PathVariable("id") int id, @RequestBody ItemsDto itemsDto) throws ItemNotFoundException{
        Items items=modelMapper.map(itemsDto,Items.class);
        final Items itemByID = itemsService.findItemById(id).orElseThrow(() -> new
                ItemNotFoundException("Item ID is Not Found!!!"));
        itemByID.setUnitPrice(items.getUnitPrice());
        itemByID.setOfferId(items.getOfferId());
        return itemsService.updateItem(itemByID);
    }
}
