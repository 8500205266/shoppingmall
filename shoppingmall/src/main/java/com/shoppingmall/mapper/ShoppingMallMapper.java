package com.shoppingmall.mapper;


import com.shoppingmall.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
/**
 * this is mapper interface which is used for mapping one class to another class
 */
public interface ShoppingMallMapper
{
    ShoppingMallMapper INSTANCE = Mappers.getMapper(ShoppingMallMapper.class);
    Customer toCustomer(CustomerDto customerDto);
    Items toItems(ItemsDto itemsDto);
    Offers toOffers(OfferDto offerDto);
}
