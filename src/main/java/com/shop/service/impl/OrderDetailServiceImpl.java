package com.shop.service.impl;

import com.shop.dto.OrderRequestDTO;
import com.shop.dto.OrderResponseDTO;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ItemNotFoundException;
import com.shop.model.Order;
import com.shop.model.OrderDetail;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderReposiory;
import com.shop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderReposiory orderReposiory;

    @Override
    public List<OrderResponseDTO> getAll(Integer customerId) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void createOrder(OrderRequestDTO requestDTO) {
        try {
            var customerOptional = customerRepository.findById(requestDTO.getCustomerId());
            var order = new Order();

            if (customerOptional.isPresent()) {
                var detail = new OrderDetail();

                if (!CollectionUtils.isEmpty(requestDTO.getOrderDetails())) {
                    order.setOrderDetails(new ArrayList<>());
                    for (var detailRequest : requestDTO.getOrderDetails()) {
                        detail.setOrder(order);
                        var itemOptional = itemRepository.findById(detailRequest.getItemId());

                        if (itemOptional.isPresent()) {
                            detail.setItem(itemOptional.get());
                        } else {
                            throw new ItemNotFoundException(String.format("Not found %s", "item"));
                        }
                        detail.setQuantity(detailRequest.getQuantity());
                        order.getOrderDetails().add(detail);
                    }
                }
                order.setCustomer(customerOptional.get());
            } else {
                throw new CustomerNotFoundException(String.format("Not found customer"));
            }
            orderReposiory.save(order);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void getLastestOrder(Integer customerId) {

    }
}
