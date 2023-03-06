package com.shop.repository;

import com.shop.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CartDetailRepository.
 * */
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

}
