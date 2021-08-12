package com.shoppingmall.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseCustomerItems
{
    private String customerName;
    private Integer customerId;
    private  HashMap<String, List<ItemsResponse>> offertype;
    private Integer totalPrice;
    private Integer freeItemsPrice;
    private  Integer dollerItemsPrice;


}

