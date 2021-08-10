package com.shoppingmall.service;

import com.shoppingmall.model.*;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RequestAndResponseService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private OffersRepository offersRepository;


    public ResponseCustomerItems AddCustomerItemsList(RequestCustomerItems requestCustomerItems) {
        ResponseCustomerItems responseCustomerItems = new ResponseCustomerItems();
        final Optional<Customer> customerbyId = customerRepository.findById(requestCustomerItems.getCustomerID());

        final List<Optional<Items>> itemsList = requestCustomerItems.getItemId().stream()
                .map(itemId -> itemsRepository.findById(itemId))
                .collect(Collectors.toList());

        final List<Optional<Offers>> offerList = requestCustomerItems.getOfferId().stream()
                .map(offerId -> offersRepository.findById(offerId))
                .collect(Collectors.toList());

        if (customerbyId.isPresent())
        {
            responseCustomerItems.setCustomerId(requestCustomerItems.getCustomerID());
            responseCustomerItems.setCustomerName(customerbyId.get().getCustomerName());

            HashMap<String, List<ItemsResponse>> offertype = new HashMap();
            List<ItemsResponse> freeItems = new ArrayList<>();
            List<ItemsResponse> dollerItem = new ArrayList<>();

            for (int i = 0; i < itemsList.size(); i++)
            {
                Optional<Items> item = itemsList.get(i);
                Optional<Offers> offers = offerList.get(i);
                ItemsResponse itemsResponse = ItemsResponse.builder()
                        .itemId(item.get().getItemId())
                        .unitPrice(item.get().getUnitPrice())
                        .build();
                if (offers.get().getOfferId() == 1)
                {
                    itemsResponse.setDiscountValue(offers.get().getDiscountValue());
                    freeItems.add(itemsResponse);
                }
                else
                {
                    itemsResponse.setDiscountValue(offers.get().getDiscountValue());
                    dollerItem.add(itemsResponse);
                }
            }
            final Map<Integer, List<ItemsResponse>> freeItemsMap = freeItems.stream().collect(Collectors.groupingBy(ItemsResponse::getItemId));
            final Map<Integer, List<ItemsResponse>> dollerItemsMap = dollerItem.stream().collect(Collectors.groupingBy(ItemsResponse::getItemId));
            List<ItemsResponse> finalFree = new ArrayList<>();
            List<ItemsResponse> finalDoller = new ArrayList<>();
            AtomicInteger totalPrice = new AtomicInteger(0);
            System.out.println("free-map"+freeItemsMap.toString());
            System.out.println("Doller-off"+dollerItemsMap.toString());
            freeItemsMap.forEach((id, list) ->
            {
                ItemsResponse itemsResponse = new ItemsResponse();
                itemsResponse.setItemId(list.get(0).getItemId());
                itemsResponse.setUnitPrice(list.get(0).getUnitPrice());
                itemsResponse.setDiscountValue(list.get(0).getDiscountValue());
                itemsResponse.setNoOfUnits(list.size());
                totalPrice.addAndGet(itemsResponse.getUnitPrice() * (itemsResponse.getNoOfUnits() / 2));
                totalPrice.addAndGet(itemsResponse.getUnitPrice() * (itemsResponse.getNoOfUnits() % 2));
                finalFree.add(itemsResponse);
            });
            dollerItemsMap.forEach((id,list) ->
            {
                ItemsResponse itemsResponse = new ItemsResponse();
                itemsResponse.setItemId(list.get(0).getItemId());
                itemsResponse.setUnitPrice(list.get(0).getUnitPrice());
                itemsResponse.setDiscountValue(list.get(0).getDiscountValue());
                itemsResponse.setNoOfUnits(list.size());
                totalPrice.addAndGet((itemsResponse.getNoOfUnits()/2)*(itemsResponse.getUnitPrice()*2)
                        -((itemsResponse.getNoOfUnits()/2)*itemsResponse.getDiscountValue()));
                totalPrice.addAndGet((itemsResponse.getNoOfUnits()%2)*itemsResponse.getUnitPrice());
                finalDoller.add(itemsResponse);

            });

            offertype.put("Free-offer", finalFree);
            offertype.put("Doller-off", finalDoller);
            responseCustomerItems.setOffertype(offertype);
            responseCustomerItems.setTotalPrice(totalPrice.get());

        } else {
            System.out.println("custmer is not found!!!!!");
        }
        return responseCustomerItems;
    }
}