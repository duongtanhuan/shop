package com.shop.mapper;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * ItemMapper.
 * */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {
  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
  
  ItemResponse toDto(Item item);
  
  Item toEntity(ItemRequest dto);
}
