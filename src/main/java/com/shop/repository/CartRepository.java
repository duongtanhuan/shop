package com.shop.repository;

import com.shop.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * CartRepository.
 * */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
  Optional<Cart> findCartByCustomerId(Integer id);
}
