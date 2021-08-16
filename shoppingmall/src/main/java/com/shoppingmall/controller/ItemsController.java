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
     *
     * @return
     */
    @GetMapping()
    public List<Items> getItems()
    {
        return itemsService.getItems();
    }

    /**
     *
     * @param itemsDto
     * @return
     */
    @PostMapping()
    public Items addItems(@RequestBody ItemsDto itemsDto)
    {
        Items items=modelMapper.map(itemsDto,Items.class);
        return itemsService.addItems(items);
    }

    /**
     *
     * @param id
     * @return
     * @throws ItemNotFoundException
     */
    @GetMapping("/{id}")
    public Items getitemById(@PathVariable("id") int id) throws ItemNotFoundException
    {
        return itemsService.findItemById(id).orElseThrow(() -> new
                ItemNotFoundException("Item ID is Not Found!!!"));
    }

    /**
     *
     * @param id
     * @throws ItemNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable("id") int id) throws ItemNotFoundException
    {
        final Items itemByID = itemsService.findItemById(id).orElseThrow(() -> new
                ItemNotFoundException("Item ID is Not Found!!!"));
        itemsService.deleteItem(itemByID);
    }

    /**
     *
     * @param id
     * @param itemsDto
     * @return
     * @throws ItemNotFoundException
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
