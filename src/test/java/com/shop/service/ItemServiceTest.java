package com.shop.service;

import com.shop.dto.ItemRequest;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.SystemErrorException;
import com.shop.mapper.ItemMapper;
import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import com.shop.service.impl.ItemServiceImpl;
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
import java.util.Optional;

/***
 * ItemServiceTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ItemServiceTest {

  @InjectMocks
  private ItemServiceImpl service;

  @Mock
  private ItemRepository repository;

  private ItemRequest request = new ItemRequest();
  private  Item item = new Item();
  
  @Before
  public void setUp() {
  }

  @Test
  public void test_get_all_success () {
    List<Item> list = new ArrayList<>();
    Mockito.when(repository.findAll()).thenReturn(list);
    var result = service.getAll();
    Assert.assertNotNull(result);
    Assert.assertEquals( list.size(), result.size());
  }

  @Test
  public void test_get_item_by_id_success () {
    Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(item));
    var result = service.findItemById(Mockito.any());
    Assert.assertEquals( ItemMapper.INSTANCE.toDto(item).getId(), result.getId());
  }
  
  @Test
  public void test_get_item_by_id_failure () {
    final Class<? extends Throwable> clz = CartNotFoundException.class;
    Mockito.when(repository.findById(Mockito.any())).thenThrow(clz);
    Assert.assertThrows(clz, () -> service.findItemById(Mockito.any()));
  }
  @Test
  public void test_add_item_success () {
    Mockito.when(repository.findById(Mockito.any())).thenReturn(null);
    Mockito.when(repository.save(Mockito.any(Item.class))).thenReturn(item);
    Item itemSaved = service.addItem(request);
    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Item.class));
    Assert.assertEquals(item.getId(), itemSaved.getId());
  }
  
  @Test
  public void test_add_item_failure () {
    final Class<? extends Throwable> clz = SystemErrorException.class;
    Mockito.when(repository.save(Mockito.any(Item.class))).thenThrow(clz);
    Assert.assertThrows(clz, () -> service.findItemById(Mockito.any()));
  }
  @Test
  public void test_update_item_success() {
    Mockito.when(repository.findById(request.getId())).thenReturn(Optional.ofNullable(item));
    Mockito.when(repository.save(Mockito.any(Item.class))).thenReturn(item);
    Item itemSaved = service.updateItem(request);
    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Item.class));
    Assert.assertEquals(item.getId(), itemSaved.getId());
  }

  @Test
  public void test_delete_item_by_id_success () {
    Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(item));
    Assert.assertTrue(service.deleteItem(item.getId()));
  }
  
}
