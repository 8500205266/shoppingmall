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
public class RequestCustomerItemsController
{
    @Autowired
    private RequestAndResponseService requestAndResponseService;

    /**
     *
     * @param requestCustomerItems
     * @return
     * @throws CustomerNotFoundException
     */
    @PostMapping
    public ResponseCustomerItems addCustomerItemsList(@RequestBody final RequestCustomerItems requestCustomerItems)
            throws  CustomerNotFoundException
            {
                return requestAndResponseService.placeOrder(requestCustomerItems);
            }

}
