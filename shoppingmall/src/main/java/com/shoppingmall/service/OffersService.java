package com.shoppingmall.service;

import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.OffersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
/**
 *it has business logic to store, retrieve, delete and updates the OffersData
 */
public class OffersService
{
    static final Logger log = LoggerFactory.getLogger(OffersService.class);
    @Autowired
    private OffersRepository offersRepository;
    /**
     *it is used to get list of Offers
     * @return list of Offers
     */
    public List<Offers> getOffers()
    {
        log.debug("List of Offers in offer service{}",offersRepository.findAll());
        return offersRepository.findAll();
    }

    /**
     *It is used to save offers data
     * @param offers which was sent from client
     * @return is used to return the saved offer
     */
    public Offers addOffers(Offers offers)
    {
        log.debug("this is new Offer-->{}",offers);
        return offersRepository.save(offers);
    }

    /**
     *it is use to send the offer data  by using corresponding offer Id
     * @param id which was given by client
     * @return offer
     */
    public Optional<Offers> findOfferById(int id)
    {
        log.debug("Offers data  by Id-->{}",offersRepository.findById(id));
        return offersRepository.findById(id);
    }

    /**
     *this used for delete the offer
     * @param offerById this offerById data  getting by using corresponding offer Id
     */
    public void deleteOffer(Offers offerById)
    {
        log.debug("delete offer--->{}",offerById);
        offersRepository.delete(offerById);
    }

    /**
     *this is used for update the offer
     * @param offerById this offer data getting by using corresponding offer Id
     * @return upadte offer data
     */
    public Offers updateOffer(Offers offerById)
    {
        log.debug("update Offer--->{}",offerById);
        return offersRepository.save(offerById);
    }
}


