package com.shoppingmall.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseCustomerItems
{
    private String customerName;
    private Integer customerId;
    private  HashMap<String, List<ItemsResponse>> offertype;
    private Integer totalPrice;



}

