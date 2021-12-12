package com.epam.adminservice.controller;

import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.service.GoodsService;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public Collection<GetGoodDto> findAll() {
        return goodsService.findAll();
    }
}
