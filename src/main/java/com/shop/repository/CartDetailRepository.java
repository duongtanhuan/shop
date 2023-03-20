package com.shop.repository;

import com.shop.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CartDetailRepository.
 * */
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
  @Modifying
  @Query("delete from CartDetail c where c.cart.id = :cartId and c.item.id = :itemId")
  void deleteCartDetailByCartIdAndItemId(@Param("cartId") Integer cartId,
                                         @Param("itemId") Integer itemId);
  
  @Query("SELECT c FROM CartDetail c WHERE c.cart.id = :cartId and c.item.id = :itemId")
   CartDetail findCartDetailByCartIdAndItemId(@Param("cartId") Integer cartId,
                                              @Param("itemId") Integer itemId);
}
