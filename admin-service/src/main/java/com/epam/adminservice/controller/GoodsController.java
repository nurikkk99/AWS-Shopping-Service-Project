package com.epam.adminservice.controller;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.UpdateGoodDto;
import com.epam.adminservice.service.GoodsService;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/{id}/image")
    public List<String> getImage(@PathVariable("id") long id) {
        return goodsService.getImage(id);
    }

    @PostMapping("/{id}/image")
    public String uploadImage(@PathVariable("id") long id,
            @RequestParam(value = "image") MultipartFile image) throws IOException {
        logger.info("Saving image entity with id good id = {}", id);
        return goodsService.saveImage(id, image);
    }
}
