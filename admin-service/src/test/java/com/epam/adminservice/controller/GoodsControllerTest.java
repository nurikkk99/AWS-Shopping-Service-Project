package com.epam.adminservice.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

import com.epam.adminservice.config.TestContainerConfig;
import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = TestContainerConfig.class)
@Testcontainers
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GoodsControllerTest {

    public static String API_PATH = "/api/goods/";

    private CreateGoodDto savedDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JdbcDatabaseContainer jdbcDatabaseContainer;

    @Autowired
    private GoodsService goodsService;

    @Before
    public void prepareData() {
        savedDto = new CreateGoodDto();
        savedDto.setId("Test");
        goodsService.save(savedDto);
    }

    @After
    public void dropData() {
        goodsService.delete(savedDto);
    }

    @Test
    public void findAllTest() throws Exception {
        final Collection<GetGoodDto> expectedCollection = goodsService.findAll();
        assertFalse("Collection is empty", expectedCollection.isEmpty());

        final String contentAsString = mockMvc.perform(get(API_PATH)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(expectedCollection.size()))).andReturn().getResponse()
                .getContentAsString();

        Collection<GetGoodDto> resultCollection = objectMapper.readValue(
                contentAsString,
                TypeFactory.defaultInstance().constructCollectionType(Collection.class, GetGoodDto.class)
        );

        assertTrue(resultCollection.containsAll(expectedCollection));
    }
}
