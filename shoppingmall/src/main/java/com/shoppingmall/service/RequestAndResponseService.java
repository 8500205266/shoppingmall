package com.shoppingmall.service;

import com.shoppingmall.exception.BadException;
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


    public ResponseCustomerItems AddCustomerItemsList(RequestCustomerItems requestCustomerItems) throws BadException {
        ResponseCustomerItems responseCustomerItems = new ResponseCustomerItems();
        final Optional<Customer> customerbyId = customerRepository.findById(requestCustomerItems.getCustomerID());

        final List<Optional<Items>> itemsList = requestCustomerItems.getItemId().stream()
                .map(itemId -> itemsRepository.findById(itemId))
                .collect(Collectors.toList());

        if (customerbyId.isPresent()) {
            responseCustomerItems.setCustomerId(requestCustomerItems.getCustomerID());
            responseCustomerItems.setCustomerName(customerbyId.get().getCustomerName());

            HashMap<String, List<ItemsResponse>> offertype = new HashMap();
            List<ItemsResponse> freeItems = new ArrayList<>();
            List<ItemsResponse> dollerItems = new ArrayList<>();

            for (int i = 0; i < itemsList.size(); i++)
            {
                System.out.println(itemsList.size());
                Optional<Items> item = itemsList.get(i);
                Optional<Offers> offers = offersRepository.findById(item.get().getOfferId());
                ItemsResponse itemsResponse = new ItemsResponse();

                if (offers.get().getOfferType().equals("free-offer")) {
                    itemsResponse.setItemId(item.get().getItemId());
                    itemsResponse.setUnitPrice(item.get().getUnitPrice());
                    itemsResponse.setItemType(offers.get().getOfferType());
                    itemsResponse.setDiscountValue(offers.get().getDiscountValue());
                    itemsResponse.setOfferId(item.get().getOfferId());
                    itemsResponse.setNoOfUnits(1);
                    freeItems.add(itemsResponse);
                } else {
                    itemsResponse.setItemId(item.get().getItemId());
                    itemsResponse.setUnitPrice(item.get().getUnitPrice());
                    itemsResponse.setItemType(offers.get().getOfferType());
                    itemsResponse.setDiscountValue(offers.get().getDiscountValue());
                    itemsResponse.setOfferId(item.get().getOfferId());
                    itemsResponse.setNoOfUnits(1);
                    dollerItems.add(itemsResponse);
                }
            }
            final List<ItemsResponse> sortedFreeOffferList = freeItems.stream().
                    sorted(Comparator.comparing(item -> item.getUnitPrice())).collect(Collectors.toList());

            final List<ItemsResponse> sortedDollerItems = dollerItems.stream().
                    sorted(Comparator.comparing(item -> item.getUnitPrice())).collect(Collectors.toList());

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
            System.out.println("freeofferprice-->"+freeofferprice);
            int  totalfreeofferprice = freeTotal - freeofferprice;
            System.out.println("totalfreeofferprice"+totalfreeofferprice);

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
            offertype.put("free-offer", sortedFreeOffferList);
            offertype.put("doller-off", sortedDollerItems);
            responseCustomerItems.setOffertype(offertype);
            responseCustomerItems.setFreeItemsPrice(totalfreeofferprice);
            responseCustomerItems.setDollerItemsPrice(dollerofferprice);
            int totalprice = totalfreeofferprice+dollerofferprice;
            responseCustomerItems.setTotalPrice(totalprice);

            return responseCustomerItems;
        } else {
            throw new BadException("custmer is not found!!!!!");
        }
    }

}
