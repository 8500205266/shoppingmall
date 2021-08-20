package com.shoppingmall.repository;

import com.shoppingmall.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 *  A interface that lets you access the database (or rather database tables),
 *  with a couple of convenience CRUD methods.
 */
public interface ItemsRepository extends JpaRepository<Items,Integer>
{

}
