package com.shoppingmall.model;

import lombok.Data;

@Data
/*
 * it has OfferId,offerType and discountValue variables
 */
public class OfferDto
{
    private Integer offerId;
    private String offerType;
    private Integer discountValue;
}
