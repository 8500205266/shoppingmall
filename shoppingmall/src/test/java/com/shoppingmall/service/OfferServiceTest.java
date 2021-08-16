package com.shoppingmall.service;

import com.shoppingmall.model.Items;
import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.OffersRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
 class OfferServiceTest
{
    @Autowired
    private OffersService offersService;

    @MockBean
    private OffersRepository offersRepository;

    @Test
     void getOffersTest()
    {
                when(offersRepository.findAll())
                .thenReturn(Stream.of(new Offers(10,"offer-type",12),
                        new Offers(11,"doller-off",15)).collect(Collectors.toList()));
                Assert.assertEquals(2,offersService.getOffers().size());
    }

    @Test
    void getOfferByIdTest() {
        Integer offerId = 10;
        when(offersRepository.findById(offerId)).
                thenReturn(Optional.of(new Offers(10, "doller-off", 12)),
                        Optional.of(new Offers(11, "free-offer", 15)));
        Assert.assertEquals(1, offersService.findOfferById(offerId).stream().count());

    }
    @Test
    void saveOfferTest()
    {
        Offers offer=new Offers(10,"doller-off",12);
        when(offersRepository.save(offer)).thenReturn(offer);
        Assert.assertEquals(offer,offersService.addOffers(offer));
    }

    @Test
    void deleteOfferTest()
    {
        Integer offerId=10;
        Offers offer=new Offers(10,"doller-off",12);
        offersService.deleteOffer(offer);
        verify(offersRepository,times(1)).delete(offer);
    }

}
