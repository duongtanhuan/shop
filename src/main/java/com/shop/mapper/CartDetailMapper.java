package com.shop.mapper;

import com.shop.dto.CartDetailResponseDTO;
import com.shop.model.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses =ItemMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartDetailMapper {
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);
    List<CartDetailResponseDTO> toDtoList(List<CartDetail> cartDetails);

}


