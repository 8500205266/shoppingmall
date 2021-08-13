package com.shoppingmall.service;

import com.shoppingmall.exception.BadException;
import com.shoppingmall.methods.Methods;
import com.shoppingmall.model.*;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestAndResponseService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private OffersRepository offersRepository;

    @Autowired(required = true)
    private Methods methods;

    public ResponseCustomerItems AddCustomerItemsList(RequestCustomerItems requestCustomerItems)
            throws BadException
    {
        ResponseCustomerItems responseCustomerItems = new ResponseCustomerItems();

        final Optional<Customer> customerbyId = customerRepository
                .findById(requestCustomerItems.getCustomerID());

        final List<Optional<Items>> itemsList = requestCustomerItems.getItemId().stream()
                .map(itemId -> itemsRepository.findById(itemId))
                .collect(Collectors.toList());

        if (customerbyId.isPresent())
        {
            responseCustomerItems.setCustomerId(requestCustomerItems.getCustomerID());
            responseCustomerItems.setCustomerName(customerbyId.get().getCustomerName());

            List<ItemsResponse> freeItems = new ArrayList<>();
            List<ItemsResponse> dollerItems = new ArrayList<>();

            for (int i = 0; i < itemsList.size(); i++)
            {
                Optional<Items> item = itemsList.get(i);
                Optional<Offers> offers = offersRepository.findById(item.get().getOfferId());
                ItemsResponse itemsResponse = new ItemsResponse();

                if (offers.get().getOfferType().equals("free-offer"))
                {
                    ItemsResponse freeitemsResponse = methods.freeOfferMethod(itemsResponse,item,offers);
                    freeItems.add(freeitemsResponse);
                }
                else
                {
                    ItemsResponse dolleritemsResponse = methods.dollerOffMethod(itemsResponse,item,offers);
                    dollerItems.add(dolleritemsResponse);
                }
            }
            final List<ItemsResponse> sortedFreeOffferList = freeItems.stream().
                    sorted(Comparator.comparing(item -> item.getUnitPrice())).collect(Collectors.toList());

            final List<ItemsResponse> sortedDollerItems = dollerItems.stream().
                    sorted(Comparator.comparing(item -> item.getUnitPrice())).collect(Collectors.toList());

            final int totalfreeofferprice = methods.freeOfferItemsPrice(sortedFreeOffferList);
            final int totaldollerofferprice=methods.dolllerOffPrrice(sortedDollerItems);

            final Map<Integer, List<ItemsResponse>> freeItemsMap = sortedFreeOffferList.stream().collect(Collectors.groupingBy(ItemsResponse::getItemId));
            final Map<Integer, List<ItemsResponse>> dollerItemsMap = sortedDollerItems.stream().collect(Collectors.groupingBy(ItemsResponse::getItemId));
            List<ItemsResponse> finalFreeOfferList = new ArrayList<>();
            List<ItemsResponse> finalDollerOffList = new ArrayList<>();
            final List<ItemsResponse> finalFreeOfferItems = methods.finalFreeOfferList(freeItemsMap, finalFreeOfferList);
            final List<ItemsResponse> finalDollerOffItems = methods.finalDollerOffList(dollerItemsMap, finalDollerOffList);

            HashMap<String, List<ItemsResponse>> offertype = new HashMap();
            offertype.put("free-offer", finalFreeOfferItems);
            offertype.put("doller-off", finalDollerOffItems);

            responseCustomerItems.setOffertype(offertype);
            responseCustomerItems.setFreeItemsPrice(totalfreeofferprice);
            responseCustomerItems.setDollerItemsPrice(totaldollerofferprice);
            int totalprice = totalfreeofferprice+totaldollerofferprice;
            responseCustomerItems.setTotalPrice(totalprice);

            return responseCustomerItems;
        }
        else
        {
                throw new BadException("custmer is not found!!!!!");
        }
    }
}
