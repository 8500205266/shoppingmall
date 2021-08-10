package com.shoppingmall.controller;

import com.shoppingmall.model.Offers;
import com.shoppingmall.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController
{
    @Autowired
    private OffersService offersService;

    @RequestMapping("/getoffers")
    public List<Offers> getOffers()
    {
        return offersService.getOffers();
    }

    @PostMapping("/addoffers")
    public Offers addOffers(@RequestBody final Offers offers)
    {
        return offersService.addOffers(offers);
    }
}
