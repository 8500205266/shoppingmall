package com.shoppingmall.service;


import com.shoppingmall.controller.OfferControllerTest;
import com.shoppingmall.datautil.UtilityModel;
import com.shoppingmall.model.OfferDto;
import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.OffersRepository;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OfferServiceTest
{
    @InjectMocks
    private OffersService offersService;

    @Mock
    private OffersRepository offersRepository;

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
        System.out.println(offer1);
        System.out.println(offer2);
        offersList =utilityModel.offersData();
        System.out.println(offersList);
    }

    @Test
    public void getOffersTest()
    {
        when(offersRepository.findAll()).thenReturn(offersList);
        System.out.println(offersService.getOffers());
        Assert.assertNotNull(offersService.getOffers());
    }

    @Test
    public void getOfferByIdTest() {
        Integer offerId = 2;
        when(offersRepository.findById(offerId)).thenReturn(Optional.ofNullable(offer1));
        Assert.assertEquals(1, offersService.findOfferById(offerId).stream().count());

    }
    @Test
    public  void saveOfferTest()
    {
        when(offersRepository.save(offer1)).thenReturn(offer1);
        Assert.assertEquals(offer1,offersService.addOffers(offer1));
    }

    @Test
    public void deleteOfferTest()
    {
        offersService.deleteOffer(offer1);
        verify(offersRepository,times(1)).delete(offer1);
    }

    @Test
    public   void updateOfferInOfferServiceTest()
    {
        when(offersRepository.save(offer1)).thenReturn(offer1);
        Assert.assertNotNull(offersService.updateOffer(offer1));
    }
}
