package com.shop.repository;

import com.shop.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * OrderRepository.
 * */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findAllByCustomerId(Integer customerId);
}
