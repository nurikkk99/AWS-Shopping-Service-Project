package com.epam.adminservice.controller;

import static org.junit.Assert.assertFalse;

import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.service.GoodsService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsControllerTest {

    private static final String AREA_PATH = "/api/goods/";

    private GoodEntity savedEntity;

    @Autowired
    private GoodsService goodsService;

    @Before
    public void prepareData(){
        savedEntity = new GoodEntity();
        savedEntity.setId("Test");
        savedEntity.setPrice(1000);
        savedEntity.setManufacturer("TestManufacturer");
        savedEntity.setName("TestName");
        savedEntity.setType("Costumes");
        LocalDate date = LocalDate.of(1,1,1);
        LocalTime time = LocalTime.of(1,1);
        savedEntity.setReleaseDate(LocalDateTime.of(date, time));
        goodsService.save(savedEntity);
    }

    @After
    public void dropData() {
        goodsService.delete(savedEntity);
    }

    @Test
    public void findAllTest() {
        final Collection<GetGoodDto> expectedCollection = goodsService.findAll();
        assertFalse("Collection is empty", expectedCollection.isEmpty());

    }
}
