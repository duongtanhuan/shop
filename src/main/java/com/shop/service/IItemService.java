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
  
  void addItem(ItemRequest request);
  
  void updateItem(ItemRequest request);
  
  void deleteItem(Integer id);
}
