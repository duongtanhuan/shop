package com.shop.service.impl;

import com.shop.dto.OrderRequest;
import com.shop.dto.OrderResponse;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.EmptyCartException;
import com.shop.exception.ItemNotFoundException;
import com.shop.mapper.OrderMapper;
import com.shop.model.Order;
import com.shop.model.OrderDetail;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.service.IOrderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * OrderDetailServiceImpl.
 * */
@Service
public class OrderDetailServiceImpl implements IOrderService {
  @Autowired
  private CustomerRepository customerRepository;
  
  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private OrderRepository orderRepository;
  
  @Override
  public List<OrderResponse> getOrdersAllByCustomerId(Integer customerId) {
    try {
      List<Order> orders = orderRepository.findAllByCustomerId(customerId);
      return orders.stream().map(OrderMapper.INSTANCE::toDto)
              .collect(Collectors.toList());
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
  
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createOrder(OrderRequest request) {
    try {
      var customerOptional = customerRepository.findById(request.getCustomerId());
      var order = new Order();
      order.setCreateDate(new Date());
      // this step is too complex, can we separate this into smaller methods ?
      if (customerOptional.isPresent()) {
        
        if (!CollectionUtils.isEmpty(customerOptional.get().getCart().getCartDetails())) {
          if (!CollectionUtils.isEmpty(request.getOrderDetails())) {
            order.setOrderDetails(new ArrayList<>());
            for (var detailRequest : request.getOrderDetails()) {
              var detail = new OrderDetail();
              var itemOptional = itemRepository.findById(detailRequest.getItemId());
              detail.setOrder(order);
              
              if (itemOptional.isPresent()) {
                detail.setItem(itemOptional.get());
              } else {
                // do not hardcode error message, please read the message from message.properties file
                throw new ItemNotFoundException(String.format("Not found item"));
              }
              detail.setQuantity(detailRequest.getQuantity());
              order.getOrderDetails().add(detail);
            }
          }
          order.setCustomer(customerOptional.get());
        } else {
          // do not hardcode error message, please read the message from message.properties file
          throw new EmptyCartException(String.format("No items in cart"));
        }
      } else {
        // do not hardcode error message, please read the message from message.properties file
        throw new CustomerNotFoundException(String.format("Not found customer"));
      }
      orderRepository.save(order);
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
  
  @Override
  public OrderResponse getLatestOrderByCustomerId(Integer customerId) {
    try {
      List<Order> orders = orderRepository.findAllByCustomerId(customerId);
      Order order = new Order();
      
      if (orders.size() > 0) {
        order = orders.get(orders.size() - 1);
      }
      return OrderMapper.INSTANCE.toDto(order);
    } catch (Exception e) {
      // do not throw e, use particular exception
      throw e;
    }
  }
}
