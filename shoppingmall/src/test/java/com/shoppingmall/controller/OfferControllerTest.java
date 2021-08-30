package com.shoppingmall.controller;

import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.exception.OfferNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsDto;
import com.shoppingmall.model.OfferDto;
import com.shoppingmall.model.Offers;
import com.shoppingmall.service.OffersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
    static Offers offer1;
    static Offers offer2;
    static OfferDto offerDto;
    UtilityModel utilityModel=new UtilityModel();
    static List<Offers> offersList;
    @Before
    public void init()
    {
        offer1=utilityModel.offersData().get(0);
        offer2=utilityModel.offersData().get(1);
        System.out.println("item1--->"+offer1);
        offersList =utilityModel.offersData();

    }


    @Test
    public void getOfferListTest()
    {
        when(offersService.getOffers()).thenReturn(offersList);
        logger.info("Offer List-->{}", offersController.getOffers());
        Assert.assertNotNull(offersController.getOffers());

    }
    @Test
    public void getOfferByOfferIdTest() throws OfferNotFoundException
    {
        final Integer  offerId=1;
        when(offersService.findOfferById(offerId)).thenReturn(Optional.of(offer1));
        logger.debug("OfferBy OfferId-->{}", offersController.getofferById(offerId));
        Assert.assertNotNull(offersController.getofferById(offerId));
    }


    @Test
    public void saveOfferInOfferControllerTest()
    {
        when(shoppingMallMapper.toOffers(offerDto)).thenReturn(offer1);
        when(offersService.addOffers(offer1)).thenReturn(offer1);
        logger.debug("new Offer--{}"+offer1);
        Assert.assertEquals(offer1, offersController.addOffers(offerDto));
    }

    @Test
    public  void deleteOfferinOfferControllerTest() throws OfferNotFoundException
    {
        int offerId=2;
        when(offersService.findOfferById(offerId)).thenReturn(Optional.of(offer2));
        offersController.deleteOfferById(offerId);
        logger.debug("Delete Offer--{}",offer2);
        verify(offersService,times(1)).deleteOffer(offer2);
    }

    @Test
    public void updateOfferInOfferControllerTest() throws OfferNotFoundException
    {
       final int offerId=1;
        when(offersService.findOfferById(offerId)).thenReturn(Optional.of(offer1));
       // Offers offers=new Offers(20,"doller-off",15);
        when(shoppingMallMapper.toOffers(offerDto)).thenReturn(offer1);
        when(offersService.updateOffer(offer1)).thenReturn(offer1);
        final Offers updateOffer = offersController.updateData(offerId,offerDto);
        logger.debug("Update offer--{}",updateOffer);
        Assert.assertNotNull(updateOffer);
    }

}
