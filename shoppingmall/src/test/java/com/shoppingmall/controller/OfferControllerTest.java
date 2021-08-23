package com.shoppingmall.controller;

import com.shoppingmall.exception.ItemsNotFound;
import com.shoppingmall.exception.OfferNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Customer;
import com.shoppingmall.model.CustomerDto;
import com.shoppingmall.model.OfferDto;
import com.shoppingmall.model.Offers;
import com.shoppingmall.service.OffersService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OfferControllerTest
{
    @InjectMocks
    private OffersController offersController;

    @Mock
    private OffersService offersService;

    @Mock
    private ShoppingMallMapper shoppingMallMapper;

    final Logger logger = LoggerFactory.getLogger(OfferControllerTest.class);

    @Test
    public void getOfferListTest()
    {

        when(offersService.getOffers()).thenReturn(Stream.of(new Offers(1,"free-offer",10),
                new Offers(2,"doller-off",10)).collect(Collectors.toList()));
        logger.info("Offer List-->{}", offersController.getOffers());
        Assert.assertNotNull(offersController.getOffers());

    }
    @Test
    public void getOfferByOfferIdTest() throws OfferNotFoundException
    {
        final Integer  offerId=1;
        when(offersService.findOfferById(offerId)).
                thenReturn(Optional.of(new Offers(1, "free-offer",10)));
        logger.debug("OfferBy OfferId-->{}", offersController.getofferById(offerId));
        Assert.assertNotNull(offersController.getofferById(offerId));
    }


    @Test
    public void saveOfferInOfferControllerTest()
    {
        OfferDto offerDto=new OfferDto(1,"free-offer",10);
        Offers offer=new Offers(1,"free-offer",10);
        when(shoppingMallMapper.toOffers(offerDto)).thenReturn(offer);
        when(offersService.addOffers(offer)).thenReturn(offer);
        logger.debug("new Offer--{}"+offer);
        Assert.assertEquals(offer, offersController.addOffers(offerDto));
    }

    @Test
    public  void deleteOfferinOfferControllerTest() throws OfferNotFoundException
    {
        int offerId=2;
        Offers offer=new Offers(2,"doller-off",20);
        when(offersService.findOfferById(offerId)).thenReturn(Optional.of(offer));
        offersController.deleteOfferById(offerId);
        logger.debug("Delete Offer--{}",offer);
        verify(offersService,times(1)).deleteOffer(offer);
    }

    @Test
    public void updateOfferInOfferControllerTest() throws OfferNotFoundException
    {
        int OfferId=20;
        when(offersService.findOfferById(20)).
                thenReturn(Optional.of(new Offers(20, "free-offer",10)));
       // Customer customer=new Customer(20,"Ram");
        OfferDto offerDto=new OfferDto(20, "free-offer",10);
        Offers offers=new Offers(20,"doller-off",15);
        when(shoppingMallMapper.toOffers(offerDto)).thenReturn(offers);
        when(offersService.updateOffer(offers)).thenReturn(offers);
        final Offers updateOffer = offersController.updateData(20,offerDto);
        logger.debug("Update offer--{}",updateOffer);
        Assert.assertNotNull(updateOffer);
    }

}
