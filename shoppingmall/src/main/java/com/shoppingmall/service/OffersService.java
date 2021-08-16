package com.shoppingmall.service;

import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OffersService
{

    @Autowired
    private OffersRepository offersRepository;

    /**
     *
     * @return
     */
    public List<Offers> getOffers()
    {
        return offersRepository.findAll();
    }

    /**
     *
     * @param offers
     * @return
     */
    public Offers addOffers(Offers offers)
    {
        return offersRepository.save(offers);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Offers> findOfferById(int id)
    {
        return offersRepository.findById(id);
    }

    /**
     *
     * @param offerById
     */
    public void deleteOffer(Offers offerById)
    {
        offersRepository.delete(offerById);
    }

    /**
     *
     * @param offerById
     * @return
     */
    public Offers updateOffer(Offers offerById)
    {
        return offersRepository.save(offerById);
    }
}


