package com.shop.service;

import com.shop.dto.ItemRequest;
import com.shop.dto.ItemResponse;
import com.shop.mapper.ItemMapper;
import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    @MockBean
    private IItemService service;
    
    @MockBean
    private ItemRepository repository;
    
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
