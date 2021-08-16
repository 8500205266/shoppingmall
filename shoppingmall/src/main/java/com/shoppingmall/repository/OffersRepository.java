package com.shoppingmall.repository;

import com.shoppingmall.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offers,Integer>
{

}
