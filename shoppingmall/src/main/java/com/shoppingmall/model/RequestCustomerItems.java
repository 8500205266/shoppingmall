package com.shoppingmall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestCustomerItems
{
    private Integer customerID;
    private List<Integer> itemId;
    private List<Integer> offerId;
}
