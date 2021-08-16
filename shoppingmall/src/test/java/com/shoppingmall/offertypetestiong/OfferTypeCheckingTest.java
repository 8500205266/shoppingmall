package com.shoppingmall.offertypetestiong;
import com.shoppingmall.utils.ItemUtil;
import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsResponse;
import com.shoppingmall.model.Offers;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;


@SpringBootTest
class OfferTypeCheckingTest
{
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private ItemUtil itemUtil;


    @Test
    void checkingFreeOfferTest()
    {
        final Optional<Items> itmesById = itemsRepository.findById(1);
        final Optional<Offers> offerById = offersRepository.findById(1);
        final ItemsResponse actualFreeOfferMethod = itemUtil.freeOfferMethod(itmesById, offerById);

        Optional<Items> items = Optional.of(new Items(1, 10, 1));
        Offers offers = new Offers(1, "free-offer", 10);
        final ItemsResponse expectedFreeOfferMethod = itemUtil.freeOfferMethod(items, Optional.of(offers));

        Assert.assertEquals(expectedFreeOfferMethod, actualFreeOfferMethod);
    }

    @Test
    void checkingDollerOffTest()
    {
        final Optional<Items> itmesById = itemsRepository.findById(2);
        final Optional<Offers> offerById = offersRepository.findById(2);
        final ItemsResponse actualDollerOffMethod = itemUtil.dollerOffMethod(itmesById, offerById);

        Optional<Items> items= Optional.of(new Items(2, 20, 2));
        Offers offers=new Offers(2,"doller-off",15);
        final ItemsResponse expectedDollerOffMethod = itemUtil.dollerOffMethod( items, Optional.of(offers));

        Assert.assertEquals(expectedDollerOffMethod,actualDollerOffMethod);
    }
}

