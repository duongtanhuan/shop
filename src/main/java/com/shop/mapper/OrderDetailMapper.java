package com.shop.mapper;

import com.shop.dto.OrderDetailResponse;
import com.shop.model.OrderDetail;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * OrderDetailMapper.
 */
@Mapper(uses = ItemMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {
  
  OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
  
  List<OrderDetailResponse> toDtoList(List<OrderDetail> details);
}
