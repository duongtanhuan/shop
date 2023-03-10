package com.shop.repository;

import com.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository.
 * */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
