package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/***
 * ItemServiceTest
 */
@RunWith(SpringRunner.class)
public class ItemServiceTest {
  
  @MockBean
  private IItemService service;
  
  private List<ItemResponse> itemList = new ArrayList<>();
  
  private ItemResponse itemResponse = new ItemResponse();
  
  private ItemRequest itemRequest = new ItemRequest();
  
  PodamFactory factory = new PodamFactoryImpl();
  @Before
  public void setUp() {
    itemList = factory.manufacturePojo(ArrayList.class, ItemResponse.class);
    itemResponse = factory.manufacturePojo(ItemResponse.class);
  }
  
  @Test
  public void test_get_all_success () {
    Mockito.when(service.getAll()).thenReturn(itemList);
    Assert.assertNotNull(itemList);
    Assert.assertEquals(itemList, service.getAll());
  }
  
  @Test
  public void test_get_item_by_id_success () {
    Mockito.when(service.findItemById(Mockito.any())).thenReturn(itemResponse);
    Assert.assertEquals(itemResponse, service.findItemById(1));
  }
  
  @Test
  public void test_add_item_success () {
    itemRequest.setName("item");
    itemRequest.setPrice(5.0);
    Mockito.when(service.addItem(Mockito.any())).thenReturn(true);
    Assert.assertEquals(true, service.addItem(itemRequest));
  }
  
  @Test
  public void test_update_item_success() {
    itemRequest.setId(1);
    itemRequest.setName("item");
    itemRequest.setPrice(5.0);
    Mockito.when(service.updateItem(Mockito.any())).thenReturn(true);
    Assert.assertTrue(service.updateItem(itemRequest));
  }
  
}
