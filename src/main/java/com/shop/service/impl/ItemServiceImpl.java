package com.shop.service.impl;

import com.shop.dto.ItemDTO;
import com.shop.exception.ItemCascadeDeleteError;
import com.shop.exception.ItemNotFoundException;
import com.shop.mapper.ItemMapper;
import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import com.shop.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<ItemDTO> getAll() throws Exception {
        try {
            return repository.getAll().stream().map(entity ->
                    ItemMapper.INSTANCE.toDto(entity)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public ItemDTO findItemById(Integer id) throws Exception {
        String msg = String.format("Not found %s", "item");
        try {
            Optional<Item> item = repository.findItemById(id);
            if (item.isPresent()) {
                return ItemMapper.INSTANCE.toDto(item.get());
            } else {
                throw new ItemNotFoundException(msg);
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void addItem(ItemDTO dto) throws Exception {
        try {
            Item item = ItemMapper.INSTANCE.toEntity(dto);
            repository.save(item);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void updateItem(ItemDTO dto) throws Exception {
        try {
            Optional<Item> item = repository.findItemById(dto.getId());
            if (item.isPresent()) {
                Item item1 = ItemMapper.INSTANCE.toEntity(dto);
                repository.save(item1);
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void deleteItem(Integer id) {
        String msg = String.format("Item delete is %s", "disabled");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ItemCascadeDeleteError(msg);
        }
    }
}
