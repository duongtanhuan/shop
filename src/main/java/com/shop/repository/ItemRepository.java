package com.shop.repository;

import com.shop.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * ItemRepository.
 * */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
