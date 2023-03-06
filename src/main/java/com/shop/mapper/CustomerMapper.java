package com.shop.mapper;

import com.shop.dto.CustomerResponse;
import com.shop.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * CustomerMapper.
 * */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  
  CustomerResponse toDto(Customer customer);
}
