package com.shoppingmall.utils;

import com.shoppingmall.model.Items;
import com.shoppingmall.model.ItemsResponse;
import com.shoppingmall.model.Offers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
public class ItemUtil
{
    private static final Logger logger = LoggerFactory.getLogger(ItemUtil.class);
    /**
     *this  method take item and offer and produce response of freeOfferType
     * @param item this will take item
     * @param offers this will take offer
     * @return which is used to send response
     */
    public ItemsResponse freeOfferMethod( Optional<Items> item , Optional<Offers> offers)
    {

            ItemsResponse itemsResponse = new ItemsResponse();
            if(item.isPresent()&&offers.isPresent())
            {
                final Integer itemId = item.get().getItemId();
                itemsResponse.setItemId(itemId);
                itemsResponse.setUnitPrice(item.get().getUnitPrice());
                itemsResponse.setItemType(offers.get().getOfferType());
                itemsResponse.setDiscountValue(offers.get().getDiscountValue());
                itemsResponse.setOfferId(item.get().getOfferId());
                itemsResponse.setNoOfUnits(1);
            }

            logger.debug("item response of freeOfferType-->{}", itemsResponse);
            return itemsResponse;
    }
    /**
     *this  method take item and offer and produce response of dollerOffType
     * @param item which required item
     * @param offers whic required ofers
     * @return which is used to return response of dolleroff
     */
    public ItemsResponse dollerOffMethod(Optional<Items> item , Optional<Offers> offers)
    {
        ItemsResponse dollerOffItemsResponse = new ItemsResponse();
        if (item.isPresent() && offers.isPresent())
        {

            dollerOffItemsResponse.setItemId(item.get().getItemId());
            dollerOffItemsResponse.setUnitPrice(item.get().getUnitPrice());
            dollerOffItemsResponse.setItemType(offers.get().getOfferType());
            dollerOffItemsResponse.setDiscountValue(offers.get().getDiscountValue());
            dollerOffItemsResponse.setOfferId(item.get().getOfferId());
            dollerOffItemsResponse.setNoOfUnits(1);
        }
        logger.debug("item response of dollerOffType-->{}", dollerOffItemsResponse);
        return dollerOffItemsResponse;
    }
    /**
     *this method which was calculate freeOfferItems price
     * @param sortedFreeOffferList which was taken sortedFreeOffferList
     * @return which is used to return  freeOfferItems price
     */
    public int freeOfferItemsPrice( List<ItemsResponse> sortedFreeOffferList)
    {
        final Integer freeOfferTypeTotalPrice = sortedFreeOffferList.stream()
                .map(ItemsResponse::getUnitPrice)
                .reduce((s1, s2) -> s1 + s2).orElse(0);
        int freeOfferSize=0;
        if(sortedFreeOffferList.size()%2==0)
        {
            freeOfferSize=sortedFreeOffferList.size();
        }
        else
        {
            freeOfferSize=sortedFreeOffferList.size()-1;
        }

        AtomicInteger freeOfferTypePairPrice= new AtomicInteger(0);
        IntStream.range(0,freeOfferSize-1).forEach(i->
        {
            if(i%2==0)
            {
                freeOfferTypePairPrice.set(freeOfferTypePairPrice.get() + sortedFreeOffferList.get(i).getUnitPrice());
            }

        });

        int  totalfreeofferprice = freeOfferTypeTotalPrice-freeOfferTypePairPrice.get();
        logger.debug("freeOfferTypeTotalPrice {}",totalfreeofferprice );
        return totalfreeofferprice;
    }

    /**
     *this method which is used to calculate dollerOffItems based on the price
     * @param sortedDollerItems which is taken sortedDollerItems
     * @return dollerOffItemsprice
     */
    public int dolllerOffPrrice(List<ItemsResponse> sortedDollerItems )
    {

        logger.info("sortedDollerItems-->{}",sortedDollerItems);
        int dolleroffSize=0;
        if(sortedDollerItems.size()%2==0)
        {
            dolleroffSize=sortedDollerItems.size();
        }
        else
        {
            dolleroffSize=sortedDollerItems.size()-1;
        }
        final Integer dollerOffTotalPrice = sortedDollerItems.stream()
                .map(ItemsResponse::getUnitPrice)
                .reduce((s1, s2) -> s1 + s2).orElse(0);
        AtomicInteger dollerOffTypePairPrice= new AtomicInteger(0);
        IntStream.range(0,dolleroffSize-1).forEach(i->
        {
            if(i%2==0)
            {
                dollerOffTypePairPrice.set(dollerOffTypePairPrice.get() + sortedDollerItems.get(i).getDiscountValue());
            }

        });
        int finalDollerOffPrice = dollerOffTotalPrice -dollerOffTypePairPrice.get();
        logger.debug("dollerOffTotalPrice--{}",finalDollerOffPrice);
        return finalDollerOffPrice;
    }

    /**
     *this method which is calculate the number of units of items
     * @param freeOfferTypeItemsMap which is required finalItemMap
     * @param finalFreeOfferTypeResponse which is required finaFree
     * @return which is provide the itemresponse
     */
    public List<ItemsResponse>  finalFreeOfferList( Map<Integer, List<ItemsResponse>>  freeOfferTypeItemsMap , List<ItemsResponse>  finalFreeOfferTypeResponse)
    {
        logger.info("freeOfferTypeItemsMap--{}",freeOfferTypeItemsMap);
        logger.info("finalFreeOfferTypeResponse--{}",finalFreeOfferTypeResponse);
        freeOfferTypeItemsMap.forEach((id, list) ->
        {
            ItemsResponse itemsResponse = new ItemsResponse();
            itemsResponse.setItemId(list.get(0).getItemId());
            itemsResponse.setUnitPrice(list.get(0).getUnitPrice());
            itemsResponse.setItemType(list.get(0).getItemType());
            itemsResponse.setDiscountValue(list.get(0).getDiscountValue());
            itemsResponse.setOfferId(list.get(0).getOfferId());
            itemsResponse.setNoOfUnits(list.size());
            finalFreeOfferTypeResponse.add(itemsResponse);
        });
        logger.info("finalFreeOfferTypeResponse-->{}",finalFreeOfferTypeResponse);
        return finalFreeOfferTypeResponse;
    }

    /**
     *this method which is calculate the number of units of items
     * @param dollerItemsMap which is required dollerItemsMap
     * @param finalDollerOffList which is required  finalDollerOffList
     * @return which is provide the itemresponse
     */
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
        logger.debug("finalDollerOffList-->{}",finalDollerOffList);
        return finalDollerOffList;
    }
}
