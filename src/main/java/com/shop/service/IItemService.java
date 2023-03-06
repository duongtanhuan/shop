package com.shop.service;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import java.util.List;

/**
 * IItemService.
 * */
public interface IItemService {
  List<ItemResponse> getAll();
  
  ItemResponse findItemById(Integer id);
  
  boolean addItem(ItemRequest request);
  
  boolean updateItem(ItemRequest request);
  
  void deleteItem(Integer id);
}
