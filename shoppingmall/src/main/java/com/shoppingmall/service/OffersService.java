package com.shoppingmall.service;

import com.shoppingmall.model.Items;
import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OffersService
{

    @Autowired
    private OffersRepository offersRepository;

    public List<Offers> getOffers()
    {
        return offersRepository.findAll();
    }

    public Offers addOffers(Offers offers)
    {
        return offersRepository.save(offers);
    }
}


