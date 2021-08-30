package com.shoppingmall.datautil;

import com.shoppingmall.model.*;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
public class UtilityModel
{
   public List<Customer> customerData()
   {
      Customer customer1=new Customer(1,"Virat Kohli");
      Customer customer2=new Customer(2,"Ms Dhoni");
      Customer customer=new Customer(18,"Saidugari Prashanth");
      List<Customer> customerList=new ArrayList<>();
      customerList.add(customer1);
      customerList.add(customer2);
      customerList.add(customer);
      return customerList;
   }
   public CustomerDto customerDtoData()
   {
      return new CustomerDto(18,"Saidugari Prashanth");
   }
   public List<Items> itemsData()
   {
      Items item1=new Items(1,10,1);
      Items item2=new Items(2,20,2);
      List<Items> itemsList=new ArrayList<>();
      itemsList.add(item1);
      itemsList.add(item2);
      return itemsList;
   }
   public  ItemsDto itemsDtoData()
   {
      ItemsDto itemDto=new ItemsDto(1,10,1);
      return itemDto;
   }
   public List<Offers> offersData()
   {
      Offers offer1=new Offers(2,"doller-off",20);
      Offers offer2=new Offers(1,"free-offer",10);
      List<Offers> offersList=new ArrayList<>();
      offersList.add(offer1);
      offersList.add(offer2);
      return offersList;
   }
   public OfferDto offerDtoData()
   {
      OfferDto offerDto=new OfferDto(1,"free-offer",10);
      return offerDto;
   }

   public HashMap<String,List<ItemsResponse>> itemDataGroupByOfferType()
   {
      HashMap<String,List<ItemsResponse>> offerTypeMap= new HashMap<>();
      List <ItemsResponse> itemsResponse1= Collections.singletonList(new ItemsResponse(2, 10, 5, 2, "free-offer", 2));
      List <ItemsResponse> itemsResponse2= Collections.singletonList(new ItemsResponse(1, 20, 10, 2, "doller-off", 1));
      offerTypeMap.put("free-offer",itemsResponse1);
      offerTypeMap.put("doller-off",itemsResponse2);
      return offerTypeMap;
   }
}
