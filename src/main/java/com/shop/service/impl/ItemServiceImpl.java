package com.shop.service.impl;

import com.shop.dto.ItemDTO;
import com.shop.mapper.ItemMapper;
import com.shop.repository.ItemRepository;
import com.shop.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<ItemDTO> getAll() {
      return repository.getAll().stream().map(entity ->
                ItemMapper.INSTANCE.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO findItemById(Integer id) {
        try {

        } catch (Exception e) {

        }
        return ItemMapper.INSTANCE.toDto(repository.findItemById(id));

    }

    @Override
    public void addItem(ItemDTO dto) {
        repository.addItem(dto);
    }

    @Override
    public void updateItem(ItemDTO dto) {

    }

    @Override
    public void deleteItem(Integer id) {

    }
}
