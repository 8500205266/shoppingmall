package com.shoppingmall.repository;

import com.shoppingmall.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items,Integer>
{

}
