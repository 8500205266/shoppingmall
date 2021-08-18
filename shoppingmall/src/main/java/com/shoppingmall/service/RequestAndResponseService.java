package com.shoppingmall.service;

import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.utils.ItemUtil;
import com.shoppingmall.model.*;
import com.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.repository.ItemsRepository;
import com.shoppingmall.repository.OffersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestAndResponseService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private OffersRepository offersRepository;

    @Autowired(required = true)
    private ItemUtil itemUtil;

    static final Logger log = LoggerFactory.getLogger(RequestAndResponseService.class);

    public ResponseCustomerItems placeOrder(RequestCustomerItems requestCustomerItems)
            throws CustomerNotFoundException
    {
        ResponseCustomerItems responseCustomerItems = new ResponseCustomerItems();

        final Optional<Customer> customerbyId = getCustomerDetails(requestCustomerItems);

        final List<Optional<Items>> itemsList = getItemDetails(requestCustomerItems);

        if (customerbyId.isPresent())
        {
            responseCustomerItems.setCustomerId(requestCustomerItems.getCustomerID());
            responseCustomerItems.setCustomerName(customerbyId.get().getCustomerName());

            List<ItemsResponse> freeItems = new ArrayList<>();
            List<ItemsResponse> dollerItems = new ArrayList<>();
            itemsList.forEach(items ->
            {
                Optional<Offers> offers = offersRepository.findById(items.get().getOfferId());

                if (offers.get().getOfferType().equals("free-offer"))
                {
                    ItemsResponse freeitemsResponse = itemUtil.freeOfferMethod(items,offers);
                    freeItems.add(freeitemsResponse);
                    log.debug("freeOffer Type Items-->"+freeItems);
                    log.info("free offer Type Items---->"+freeItems);
                }
                else
                {
                    ItemsResponse dolleritemsResponse = itemUtil.dollerOffMethod(items,offers);
                    dollerItems.add(dolleritemsResponse);
                    log.debug("doller off Type Items-->"+dollerItems);
                    log.info("doller off Type Items---->"+dollerItems);
                }
            });
            final List<ItemsResponse> sortedFreeOfferList = sortOnUnitPrice(freeItems);

            final List<ItemsResponse> sortedDollarItems = sortOnUnitPrice(dollerItems);

            final int totalFreeOfferPrice = itemUtil.freeOfferItemsPrice(sortedFreeOfferList);
            final int totalDollerOfferPrice = itemUtil.dolllerOffPrrice(sortedDollarItems);

            final Map<Integer, List<ItemsResponse>> freeItemsMap = groupByItemId(sortedFreeOfferList);
            final Map<Integer, List<ItemsResponse>> dollerItemsMap = groupByItemId(sortedDollarItems);
            List<ItemsResponse> finalFreeOfferList = new ArrayList<>();
            List<ItemsResponse> finalDollerOffList = new ArrayList<>();

            final List<ItemsResponse> finalFreeOfferItems = itemUtil.finalFreeOfferList(freeItemsMap, finalFreeOfferList);
            final List<ItemsResponse> finalDollerOffItems = itemUtil.finalDollerOffList(dollerItemsMap, finalDollerOffList);

            HashMap<String, List<ItemsResponse>> offertype = new HashMap();
            offertype.put("free-offer", finalFreeOfferItems);
            offertype.put("doller-off", finalDollerOffItems);
            responseCustomerItems.setOffertype(offertype);

            responseCustomerItems.setFreeItemsPrice(totalFreeOfferPrice);
            responseCustomerItems.setDollerItemsPrice(totalDollerOfferPrice);
            int totalprice = totalFreeOfferPrice + totalDollerOfferPrice;
            responseCustomerItems.setTotalPrice(totalprice);
            return responseCustomerItems;
        } else {
            throw new CustomerNotFoundException("custmer is not found!!!!!");
        }
    }

    /**
     *
     * @param sortedFreeOfferList
     * @return
     */
    private Map<Integer, List<ItemsResponse>> groupByItemId(List<ItemsResponse> sortedFreeOfferList)
    {
        return sortedFreeOfferList.stream().collect(Collectors.groupingBy(ItemsResponse::getItemId));
    }
    /**
     *
     * @param freeItems
     * @return
     */
    private List<ItemsResponse> sortOnUnitPrice(List<ItemsResponse> freeItems) {
        return freeItems.stream().
                sorted(Comparator.comparing(ItemsResponse::getUnitPrice)).collect(Collectors.toList());
    }

    /**
     *
     * @param item
     * @return
     */
    private Optional<Offers> getOffer(Optional<Items> item) {
        return offersRepository.findById(item.get().getOfferId());
    }

    /**
     *
     * @param requestCustomerItems
     * @return
     */
    private List<Optional<Items>> getItemDetails(RequestCustomerItems requestCustomerItems) {
        return requestCustomerItems.getItemId().stream()
                .map(itemId -> itemsRepository.findById(itemId))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param requestCustomerItems
     * @return
     */
    private Optional<Customer> getCustomerDetails(RequestCustomerItems requestCustomerItems) {
        return customerRepository
                .findById(requestCustomerItems.getCustomerID());
    }
}
