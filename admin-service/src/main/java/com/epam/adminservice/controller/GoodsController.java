package com.epam.adminservice.controller;

import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.service.GoodsService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public Collection<GetGoodDto> findAll() {
        return goodsService.findAll();
    }
}
