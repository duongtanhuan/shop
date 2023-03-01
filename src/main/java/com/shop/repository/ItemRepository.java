package com.shop.repository;

import com.shop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT i.* FROM item i", nativeQuery = true)
    List<Item> getAll();

    @Query(value = "SELECT i.* FROM item i"
            + " WHERE i.id = :#{#id}",
            nativeQuery = true)
    Optional<Item> findItemById(@Param("id") Integer id);

}
