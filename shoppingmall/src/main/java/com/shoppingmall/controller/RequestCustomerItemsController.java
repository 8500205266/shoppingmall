package com.shoppingmall.controller;


import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.model.RequestCustomerItems;
import com.shoppingmall.model.ResponseCustomerItems;
import com.shoppingmall.service.RequestAndResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
/**
 * this is controller class whis is used for request the order
 */
public class RequestCustomerItemsController
{
    @Autowired
    private RequestAndResponseService requestAndResponseService;

    /**
     *we will provide customer Id and items here
     * @param requestCustomerItems it has customer id and list of items
     * @return list of items with offer id and customer Id and total price of items
     * @throws CustomerNotFoundException if customer id not found then it returns CustomerNotFoundException
     */
    @PostMapping
    public ResponseCustomerItems addCustomerItemsList(@RequestBody final RequestCustomerItems requestCustomerItems)
            throws CustomerNotFoundException
            {
                return requestAndResponseService.placeOrder(requestCustomerItems);
            }

}
