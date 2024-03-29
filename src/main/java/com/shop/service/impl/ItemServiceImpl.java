package com.shop.service.impl;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.exception.ItemCascadeDeleteError;
import com.shop.exception.ItemNotFoundException;
import com.shop.exception.SystemErrorException;
import com.shop.mapper.ItemMapper;
import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import com.shop.service.IItemService;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * ItemServiceImpl.
 * */
@Service
public class ItemServiceImpl implements IItemService {
  @Autowired
  private ItemRepository repository;
  
  @Autowired
  private MessageSource messageSource;
  
  @Override
  public List<ItemResponse> getAll() {
    try {
      return repository.findAll().stream().map(ItemMapper.INSTANCE::toDto)
              .collect(Collectors.toList());
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL101", null, Locale.ENGLISH));
    }
  }
  
  @Override
  public ItemResponse findItemById(Integer id) {
    try {
    
    Optional<Item> item = repository.findById(id);
    
    if (item.isPresent()) {
      return ItemMapper.INSTANCE.toDto(item.get());
    } else {
      throw new ItemNotFoundException(messageSource.getMessage("EBL102A", null, Locale.ENGLISH));
    }
    } catch (ItemNotFoundException e) {
      throw new ItemNotFoundException(messageSource.getMessage("EBL102B", null, Locale.ENGLISH));
    }
  }
  
  @Override
  public Item addItem(ItemRequest request) {
    try {
      Item item = ItemMapper.INSTANCE.toEntity(request);
      return repository.save(item);
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL103", null, Locale.ENGLISH));
    }
  }
  
  @Override
  public Item updateItem(ItemRequest request) {
    try {
      var itemOption =  repository.findById(request.getId());
      
      if (itemOption.isPresent()) {
        return repository.save(ItemMapper.INSTANCE.toEntity(request));
      } else {
        throw new ItemNotFoundException(messageSource.getMessage("EBL102A", null, Locale.ENGLISH));
      }
    } catch (ItemNotFoundException e) {
      throw new ItemNotFoundException(messageSource.getMessage("EBL102B", null, Locale.ENGLISH));
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL104", null, Locale.ENGLISH));
    }
  }
  
  @Override
  public Boolean deleteItem(Integer id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      throw new ItemCascadeDeleteError(messageSource.getMessage("EBL105", null,
              Locale.ENGLISH));
    }
    return true;
  }
}
