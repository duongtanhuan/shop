package com.shop.service;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.model.Item;
import java.util.List;

/**
 * IItemService.
 * */
public interface IItemService {
  List<ItemResponse> getAll();
  
  ItemResponse findItemById(Integer id);
  
  Item addItem(ItemRequest request);
  
  Item updateItem(ItemRequest request);
  
  Boolean deleteItem(Integer id);
}
