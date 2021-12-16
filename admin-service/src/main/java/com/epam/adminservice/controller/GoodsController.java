package com.epam.adminservice.controller;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.UpdateGoodDto;
import com.epam.adminservice.service.GoodsService;
import java.util.Collection;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public CreateGoodDto save(@RequestBody @Valid CreateGoodDto goodDto) {
        logger.info("Saving good entity with id = {}", goodDto.getId());
        return goodsService.save(goodDto);
    }

    @GetMapping("/{id}")
    public GetGoodDto findOne(@PathVariable("id") Long id) {
        return goodsService.findOne(id);
    }

    @PutMapping("/{id}")
    public UpdateGoodDto updateOne(@PathVariable("id") long id, @RequestBody @Valid UpdateGoodDto goodDto) {
        logger.info("Updating good entity with id = {}", goodDto.getId());
        return goodsService.update(id, goodDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable("id") long id) {
        logger.info("Deleting good entity with id = {}", id);
        goodsService.deleteOne(id);
        return ResponseEntity.ok().body("Entity with id "+id+" was deleted");
    }
}
