package com.shop.mapper;

import com.shop.dto.CartDetailResponse;
import com.shop.model.CartDetail;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


/**
 * CartDetailMapper.
 * */
@Mapper(uses = ItemMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartDetailMapper {
  CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);
  
  List<CartDetailResponse> toDtoList(List<CartDetail> cartDetails);
}


