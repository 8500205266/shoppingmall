package com.shoppingmall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/*
 * it has ItemId,unitprice and discountValue,noOfUnits and itemType, offerId variables
 */
public class ItemsResponse
{

    private Integer itemId;
    private Integer unitPrice;
    private Integer discountValue;
    private Integer noOfUnits;
    private String itemType;
    private Integer offerId;
}
