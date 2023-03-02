package com.shop.mapper;

import com.shop.dto.CartResponseDTO;
import com.shop.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponseDTO toDto(Cart cart);
}
