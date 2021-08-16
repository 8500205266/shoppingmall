package com.shoppingmall.controller;

import com.shoppingmall.exception.OfferNotFoundException;
import com.shoppingmall.model.OfferDto;
import com.shoppingmall.model.Offers;
import com.shoppingmall.service.OffersService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    private String offerIdNotFound="Offer ID is Not Found!!!";

    /**
     *
     * @return
     */
    @RequestMapping()
    public List<Offers> getOffers()
    {
        return offersService.getOffers();
    }

    /**
     *
     * @param offerDto
     * @return
     */
    @PostMapping()
    public Offers addOffers(@RequestBody OfferDto offerDto)
    {
        Offers offers=modelMapper.map(offerDto,Offers.class);
        return offersService.addOffers(offers);
    }

    /**
     *
     * @param id
     * @return
     * @throws OfferNotFoundException
     */
    @GetMapping("/{id}")
    public Offers getofferById(@PathVariable("id") int id) throws OfferNotFoundException
    {
        return offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
    }

    /**
     *
     * @param id
     * @throws OfferNotFoundException
     */
    @DeleteMapping("/{id}")
    public void deleteOfferById(@PathVariable("id") int id) throws OfferNotFoundException
    {
        final Offers offerById = offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
        offersService.deleteOffer(offerById);
    }


    /**
     *
     * @param id
     * @param offerDto
     * @return
     * @throws OfferNotFoundException
     */
    @PutMapping("/{id}")
    public Offers updateData(@PathVariable("id") int id, @RequestBody OfferDto offerDto)
            throws OfferNotFoundException
    {
        Offers offers=modelMapper.map(offerDto,Offers.class);
        final Offers offerById = offersService.findOfferById(id).orElseThrow(() -> new
                OfferNotFoundException(offerIdNotFound));
        offerById.setOfferType(offers.getOfferType());
        offerById.setDiscountValue(offers.getDiscountValue());
        return offersService.updateOffer(offerById);
    }

}
