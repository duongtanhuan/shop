package com.shop.service;

import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ItemServiceTest {

    @Autowired
    private IItemService service;

    @MockBean
    private ItemRepository repository;

    private List<Item> itemList = new ArrayList<>();

    @Before
    public void setUp() {

    }

    @Test
    public void test_get_all_success () {
        Mockito.when(service.getAll().size()).thenReturn(3);
        var rerult = service.getAll().size();
        Assert.assertTrue(rerult > 0);
    }

    @Test
    public void test_get_item_by_id_success () {
    }

}
