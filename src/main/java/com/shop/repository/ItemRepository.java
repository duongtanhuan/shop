package com.shop.repository;

import com.shop.dto.ItemDTO;
import com.shop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT i.* FROM item i", nativeQuery = true)
    List<Item> getAll();

    @Query(value = "SELECT i.* FROM item i"
            + " WHERE i.id = :#{#id}",
            nativeQuery = true)
    Item findItemById(@Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO item" +
            " (price)" +
            " VALUES (8)"
            , nativeQuery = true)
    int addItem(@Param("dto") ItemDTO dto);

//    @Modifying
//    @Query(value = "UPDATE item i SET" +
//            " i.name = :#{#dto.name}," +
//            " i.price = :#{#dto.price}" +
//            " WHERE i.id = #{#dto.id}",
//            nativeQuery = true)
//    void updateItem(@Param("dto")ItemDTO dto);

//    @Modifying
//    @Query(value = "DELETE FROM item i" +
//            " WHERE i.id = :#{#id}",
//            nativeQuery = true)
//    void deleteItemById(@Param("id") Integer id);

}
