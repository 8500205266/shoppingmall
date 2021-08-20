package com.shoppingmall.model;

import lombok.Data;


/*
 * it has ItemId,unitprice and offerId variables
 */
@Data
public class ItemsDto
{
    private Integer itemId;
    private Integer unitPrice;
    private Integer offerId;
}
