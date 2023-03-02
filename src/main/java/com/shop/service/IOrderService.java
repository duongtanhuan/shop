package com.shop.service;

import com.shop.dto.OrderRequestDTO;
import com.shop.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IOrderService {

    List<OrderResponseDTO>  getAll(Integer customerId);

    void createOrder(OrderRequestDTO requestDTO);

    void getLastestOrder(Integer customerId);
}
