package com.shop.service;

import com.shop.dto.ItemDTO;

import java.util.List;

public interface IItemService {
    List<ItemDTO> getAll();

    ItemDTO findItemById(Integer id);

    void addItem(ItemDTO dto);

    void updateItem(ItemDTO dto);

    void deleteItem(Integer id);
}
