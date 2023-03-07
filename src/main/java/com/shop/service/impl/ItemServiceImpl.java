package com.shop.service.impl;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.exception.ItemCascadeDeleteError;
import com.shop.exception.ItemNotFoundException;
import com.shop.mapper.ItemMapper;
import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import com.shop.service.IItemService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ItemServiceImpl.
 * */
@Service
public class ItemServiceImpl implements IItemService {
  @Autowired
  private ItemRepository repository;
  
  @Override
  public List<ItemResponse> getAll() {
    try {
      return repository.findAll().stream().map(ItemMapper.INSTANCE::toDto)
              .collect(Collectors.toList());
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
  
  @Override
  public ItemResponse findItemById(Integer id) {
    try {
      Optional<Item> item = repository.findById(id);
      if (item.isPresent()) {
        return ItemMapper.INSTANCE.toDto(item.get());
      } else {
        // do not hardcode error message, please read the message from message.properties file
        throw new ItemNotFoundException(String.format("Not found %s", "item"));
      }
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
  
  @Override
  public boolean addItem(ItemRequest request) {
    try {
      Item item = ItemMapper.INSTANCE.toEntity(request);
      repository.save(item);
    } catch (Exception e) {
      // user particular exception
      throw e;
    }
    return true;
  }
  
  @Override
  public boolean updateItem(ItemRequest request) {
    try {
      Optional<Item> item = repository.findById(request.getId());
      if (item.isPresent()) {
        Item item1 = ItemMapper.INSTANCE.toEntity(request);
        repository.save(item1);
      }
    } catch (Exception e) {
      // use particular exception
      throw e;
    }
    return true;
  }
  
  @Override
  public void deleteItem(Integer id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      // do not hardcode error message, please read the message from message.properties file
      throw new ItemCascadeDeleteError(String.format("Item delete is %s", "disabled"));
    }
  }
}
