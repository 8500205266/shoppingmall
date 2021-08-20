package com.shoppingmall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
/*
 * it has customerId,itemId variables
 */
public class RequestCustomerItems
{
    private Integer customerID;
    private List<Integer> itemId;
}
