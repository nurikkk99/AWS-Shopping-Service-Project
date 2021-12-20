package com.epam.adminservice.service;

import static org.junit.Assert.assertThrows;

import com.epam.adminservice.config.TestContainerConfig;
import com.epam.adminservice.dto.CreateGoodDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
    public void prepareData() {
        CreateGoodDto goodDto = new CreateGoodDto();
        savedDto = goodsService.save(goodDto);
    }

    @After
    public void dropData() {
        goodsService.deleteAll();
    }

    @Test
    public void test() {

    }
}
