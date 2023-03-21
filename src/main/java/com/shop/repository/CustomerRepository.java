package com.shop.repository;

import com.shop.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository.
 * */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//  Optional<Customer> findByUserName(@Param("username") String username);
}
