package com.epam.adminservice.service;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertFalse;

import com.epam.adminservice.config.TestContainerConfig;
import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.dto.GoodsType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = TestContainerConfig.class)
@Testcontainers
@RunWith(SpringRunner.class)
public class GoodServiceTest {

    private CreateGoodDto savedDto;

    @Autowired
    private JdbcDatabaseContainer jdbcDatabaseContainer;

    @Autowired
    private GoodsService goodsService;

    @Before
    public void prepareData(){
        savedDto = new CreateGoodDto();
        savedDto.setId("Test");
        goodsService.save(savedDto);
    }

    @After
    public void dropData() {
        goodsService.delete(savedDto);
    }

    @Test
    public void findAllTest() {
        final Collection<GetGoodDto> actualCollection = goodsService.findAll();
        assertFalse("Collection is empty", actualCollection.isEmpty());
        List<GetGoodDto> expectedCollection = new ArrayList<>();
        GetGoodDto getGoodDto = new GetGoodDto();
        getGoodDto.setId(savedDto.getId());
        expectedCollection.add(getGoodDto);
        assertTrue(expectedCollection.containsAll(actualCollection));
    }
}
