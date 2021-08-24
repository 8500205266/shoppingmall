package com.shoppingmall.controller;
import com.shoppingmall.enumdatatype.ExceptionNamesWithCode;
import com.shoppingmall.exception.OfferNotFoundException;
import com.shoppingmall.mapper.ShoppingMallMapper;
import com.shoppingmall.model.OfferDto;
import com.shoppingmall.model.Offers;
import com.shoppingmall.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/offers")
public class OffersController
{
    @Autowired
    private OffersService offersService;
    @Autowired
    private ShoppingMallMapper shoppingMallMapper;


    ExceptionNamesWithCode.Error offerIdNotFound=ExceptionNamesWithCode.Error.OFFERNOTFOUND;

    /**
     *it is used to return all offers
     * @return
     */
    @RequestMapping()
    public List<Offers> getOffers()
    {
        return offersService.getOffers();
    }

    /**
     *it is use to save offer
     * @param offerDto it will take offer
     * @return it is used to return offer which was saved
     */
    @PostMapping()
    public Offers addOffers(@RequestBody OfferDto offerDto)
    {
        Offers offers=shoppingMallMapper.toOffers(offerDto);
        return offersService.addOffers(offers);
    }

    /**
     *It is provides offer details when we pass offerId
     * @param id we will pass offer id
     * @return it is return the offers corresponding that offerId
     * @throws OfferNotFoundException if offerid not there then it throws OfferNotFoundException
     */
    @GetMapping("/{id}")
    public Offers getofferById(@PathVariable("id") int id) throws OfferNotFoundException
    {
        return offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
    }

    /**
     *It is used to delete offer when we pass offerId of offer
     * @param id pass the offer id
     * @throws OfferNotFoundException if id is not Found then it throws OfferNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteOfferById(@PathVariable("id") int id) throws OfferNotFoundException
    {
        final Offers offerById = offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
        offersService.deleteOffer(offerById);
    }


    /**
     *It is used to update offer when we pass offerId of offer
     * @param id pass the offer id
     * @param offerDto we will pass offer
     * @return it is return updated offer
     * @throws OfferNotFoundException if offer not found then it throws custom OfferNotFoundException
     */
    @PutMapping("/{id}")
    public Offers updateData(@PathVariable("id") int id, @RequestBody OfferDto offerDto)
            throws OfferNotFoundException
    {
        final Offers offerById = offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
        Offers offers=shoppingMallMapper.toOffers(offerDto);
        offerById.setOfferType(offers.getOfferType());
        offerById.setDiscountValue(offers.getDiscountValue());
        return offersService.updateOffer(offerById);
    }

}
