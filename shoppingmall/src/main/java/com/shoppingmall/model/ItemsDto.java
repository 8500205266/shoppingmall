package com.shoppingmall.model;

import lombok.*;


/*
 * it has ItemId,unitprice and offerId variables
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemsDto
{
    private Integer itemId;
    private Integer unitPrice;
    private Integer offerId;
}
