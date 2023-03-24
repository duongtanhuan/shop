package com.shop.repository;

import com.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CustomerRepository.
 * */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  @Query("SELECT c FROM Customer c WHERE c.username = :username")
  Optional<Customer> findByUsername(@Param("username") String username);
  
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
