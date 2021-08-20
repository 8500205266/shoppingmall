package com.shoppingmall.repository;

import com.shoppingmall.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * A interface that lets you access the database (or rather database tables),
 * with a couple of convenience CRUD methods.
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
}
