package com.shop.service;

import com.shop.dto.ItemDTO;

import java.util.List;

public interface IItemService {
    List<ItemDTO> getAll() throws Exception;

    ItemDTO findItemById(Integer id) throws Exception;

    void addItem(ItemDTO dto) throws Exception;

    void updateItem(ItemDTO dto) throws Exception;

    void deleteItem(Integer id);
}
