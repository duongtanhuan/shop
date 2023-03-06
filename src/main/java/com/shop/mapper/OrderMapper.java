package com.shop.mapper;

import com.shop.dto.OrderResponse;
import com.shop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * OrderMapper.
 * */
@Mapper(uses = {CustomerMapper.class, ItemMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
  
  OrderResponse toDto(Order order);
}
