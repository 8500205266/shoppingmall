package com.shoppingmall.model;

import lombok.Data;

@Data
public class OfferDto
{
    private Integer offerId;
    private String offerType;
    private Integer discountValue;
}
