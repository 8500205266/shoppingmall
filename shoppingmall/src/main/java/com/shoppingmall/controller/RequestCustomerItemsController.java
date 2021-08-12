package com.shoppingmall.controller;

import com.shoppingmall.exception.BadException;
import com.shoppingmall.model.RequestCustomerItems;
import com.shoppingmall.model.ResponseCustomerItems;
import com.shoppingmall.service.RequestAndResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestCustomerItemsController
{
    @Autowired
    private RequestAndResponseService requestAndResponseService;

    @PostMapping("/customeritemsrequest")
    public ResponseCustomerItems AddCustomerItemsList(@RequestBody final RequestCustomerItems requestCustomerItems) throws BadException {
        return requestAndResponseService.AddCustomerItemsList(requestCustomerItems);
    }
}
