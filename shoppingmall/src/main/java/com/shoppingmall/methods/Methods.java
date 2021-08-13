package com.shoppingmall.methods;

import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsResponse;
import com.shoppingmall.model.Offers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Methods
{
    public ItemsResponse freeOfferMethod( ItemsResponse itemsResponse,Optional<Items> item , Optional<Offers> offers)
    {
        itemsResponse.setItemId(item.get().getItemId());
        itemsResponse.setUnitPrice(item.get().getUnitPrice());
        itemsResponse.setItemType(offers.get().getOfferType());
        itemsResponse.setDiscountValue(offers.get().getDiscountValue());
        itemsResponse.setOfferId(item.get().getOfferId());
        itemsResponse.setNoOfUnits(1);
        return itemsResponse;
    }

    public ItemsResponse dollerOffMethod(ItemsResponse itemsResponse,Optional<Items> item , Optional<Offers> offers)
    {
        itemsResponse.setItemId(item.get().getItemId());
        itemsResponse.setUnitPrice(item.get().getUnitPrice());
        itemsResponse.setItemType(offers.get().getOfferType());
        itemsResponse.setDiscountValue(offers.get().getDiscountValue());
        itemsResponse.setOfferId(item.get().getOfferId());
        itemsResponse.setNoOfUnits(1);
        return itemsResponse;
    }

    public int freeOfferItemsPrice( List<ItemsResponse> sortedFreeOffferList)
    {
        Integer freeofferprice=0;
        int freeTotal = sortedFreeOffferList.stream().map(ItemsResponse::getUnitPrice).reduce((s1,s2) -> s1+s2).get();

        System.out.println("freetotal->"+freeTotal);

        int freeOfferSize=0;
        if(sortedFreeOffferList.size()%2==0)
        {
            freeOfferSize=sortedFreeOffferList.size();
        }
        else
        {
            freeOfferSize=sortedFreeOffferList.size()-1;
        }
        for (int i = 0; i <freeOfferSize; i=i+2)
        {
            freeofferprice=freeofferprice+sortedFreeOffferList.get(i).getUnitPrice();
        }
        int  totalfreeofferprice = freeTotal - freeofferprice;
        return totalfreeofferprice;
    }

    public int dolllerOffPrrice(List<ItemsResponse> sortedDollerItems )
    {

        int dolleroffSize=0;
        if(sortedDollerItems.size()%2==0)
        {
            dolleroffSize=sortedDollerItems.size();
        }
        else
        {
            dolleroffSize=sortedDollerItems.size()-1;
        }

        int dollerTotal = sortedDollerItems.stream().map(ItemsResponse::getUnitPrice).reduce((s1,s2)->s1+s2).get();

        System.out.println("price-->"+dollerTotal);
        Integer dollerofferprice=0;
        for (int i = 0; i < dolleroffSize; i=i+2)
        {
            dollerofferprice=dollerofferprice+sortedDollerItems.get(i).getDiscountValue();
        }
        dollerofferprice = dollerTotal - dollerofferprice;
        return dollerofferprice;
    }

    public List<ItemsResponse>  finalFreeOfferList( Map<Integer, List<ItemsResponse>>  freeItemsMap , List<ItemsResponse>  finalFree)
    {
        freeItemsMap.forEach((id, list) ->
        {
            ItemsResponse itemsResponse = new ItemsResponse();
            itemsResponse.setItemId(list.get(0).getItemId());
            itemsResponse.setUnitPrice(list.get(0).getUnitPrice());
            itemsResponse.setItemType(list.get(0).getItemType());
            itemsResponse.setDiscountValue(list.get(0).getDiscountValue());
            itemsResponse.setOfferId(list.get(0).getOfferId());
            itemsResponse.setNoOfUnits(list.size());
            finalFree.add(itemsResponse);
        });
        return finalFree;
    }

    public List<ItemsResponse> finalDollerOffList( Map<Integer, List<ItemsResponse>>  dollerItemsMap , List<ItemsResponse> finalDollerOffList)
    {
        dollerItemsMap.forEach((id, list) ->
        {
            ItemsResponse itemsResponse = new ItemsResponse();
            itemsResponse.setItemId(list.get(0).getItemId());
            itemsResponse.setUnitPrice(list.get(0).getUnitPrice());
            itemsResponse.setItemType(list.get(0).getItemType());
            itemsResponse.setDiscountValue(list.get(0).getDiscountValue());
            itemsResponse.setOfferId(list.get(0).getOfferId());
            itemsResponse.setNoOfUnits(list.size());
            finalDollerOffList.add(itemsResponse);
        });
        return finalDollerOffList;
    }
}
