package com.shop.service;


import com.shop.model.Item;
import com.shop.model.Order;
import com.shop.repository.OrderRepository;
import com.shop.service.impl.OrderDetailServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/***
 * OrderServiceTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class OrderServiceTest {
  @InjectMocks
  private OrderDetailServiceImpl orderDetailService;

  @Mock
  private OrderRepository orderRepository;
  
  @Before
  public void setUp() {
  }
  
  @Test
  public void test_get_all_order_success () {
    List<Order> list = new ArrayList<>();
    Mockito.when(orderRepository.findAllByCustomerId(Mockito.any())).thenReturn(list);
    var result = orderDetailService.getOrdersAllByCustomerId(Mockito.any());
    Assert.assertNotNull(result);
    Assert.assertEquals( list.size(), result.size());
  }
}
