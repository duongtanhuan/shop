package com.shop.service;

import com.shop.dto.CartDetailRequest;
import com.shop.dto.CartRequest;
import com.shop.exception.CartDetailCascadeDeleteError;
import com.shop.exception.CartNotFoundException;
import com.shop.exception.CustomerNotFoundException;
import com.shop.exception.ItemNotFoundException;
import com.shop.model.Cart;
import com.shop.model.CartDetail;
import com.shop.model.Customer;
import com.shop.model.Item;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ItemRepository;
import com.shop.service.impl.CartServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.SystemException;
import java.util.Optional;

/***
 * CartServiceTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

  @InjectMocks
  private CartServiceImpl service;
  
  @Mock
  private CartRepository repository;
  @Mock
  private CustomerRepository customerRepository;
  @Mock
  private ItemRepository itemRepository;
  @Mock
  private CartDetailRepository cartDetailRepository;
  private CartRequest cartRequest = new CartRequest();
  private Cart  cart = new Cart();
  private Customer  customer = new Customer();
  private Item  item = new Item();
  private CartDetail cartDetail  = new CartDetail();
  @Before
  public void setUp() {
  }
  
  @Test
  public void test_get_all_success () {
    Mockito.when(repository.findCartByCustomerId(Mockito.any())).thenReturn(Optional.ofNullable(cart));
    var result = service.findCartByCustomerId(Mockito.any());
    Assert.assertNotNull(result);
    Assert.assertEquals(result.getId(), cart.getId());
  }
  
  @Test
  public void test_get_all_failure () {
    final Class<? extends Throwable> clz = CartNotFoundException.class;
    Mockito.when(repository.findCartByCustomerId(Mockito.any())).thenThrow(clz);
    Assert.assertThrows(clz, () -> service.findCartByCustomerId(Mockito.any()));
  }
  
  @Test
  public void test_add_item_to_cart_success () {
    cartRequest.setCustomerId(1);
    var cartDetailRequest = new CartDetailRequest();
    cartDetailRequest.setItemId(1);
    cartDetailRequest.setQuantity(1);
    cartRequest.setCartDetail(cartDetailRequest);
    Mockito.when(repository.findById(Mockito.any())).thenReturn(null);
    Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(customer));
    Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(item));
    Mockito.when(repository.save(Mockito.any(Cart.class))).thenReturn(cart);
    var cartSaved = service.addItemsToCart(cartRequest);
    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Cart.class));
    Assert.assertEquals(cart.getId(), cartSaved.getId());
  }
  
  @Test
  public void test_add_item_to_cart_failure () {
    final Class<? extends Throwable> clz = Exception.class;
    Mockito.when(customerRepository.findById(Mockito.any())).thenThrow(CustomerNotFoundException.class);
    Mockito.when(itemRepository.findById(Mockito.any())).thenThrow(ItemNotFoundException.class);
    Mockito.when(repository.save(Mockito.any(Cart.class))).thenThrow(SystemException.class);
    Assert.assertThrows(clz, () -> service.addItemsToCart(Mockito.any(CartRequest.class)));
  }
  
  @Test
  public void test_update_cart_success () {
    cart.setId(1);
    cartRequest.setCustomerId(1);
    var cartDetailRequest = new CartDetailRequest();
    cartDetailRequest.setId(1);
    cartDetailRequest.setItemId(1);
    cartDetailRequest.setQuantity(1);
    cartRequest.setCartDetail(cartDetailRequest);
    Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(cart));
    Mockito.when(repository.findCartByCustomerId(Mockito.any())).thenReturn(Optional.ofNullable(cart));
    Mockito.when(cartDetailRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(cartDetail));
    Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(item));
    service.updateItemsToCart(cartRequest);
    var cartOptional = repository.findById(cartRequest.getId());
    Mockito.verify(cartDetailRepository, Mockito.times(1)).save(Mockito.any(CartDetail.class));
    Assert.assertEquals(cart.getId(), cartOptional.get().getId());
  }
  
  @Test
  public void test_delete_cart_detail_by_id_success () {
    Mockito.when(cartDetailRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(cartDetail));
    service.deleteItemInCartByCartDetailId(cartDetail.getId());
  }
  
  @Test
  public void test_delete_cart_detail_by_id_Failure () {
    final Class<? extends Throwable> clz = CartDetailCascadeDeleteError.class;
    Mockito.when(service.findCartByCustomerId(Mockito.any())).thenThrow(clz);
    Assert.assertThrows(clz, () -> service
            .deleteItemInCartByCartDetailId(Mockito.any()));
  }

}
