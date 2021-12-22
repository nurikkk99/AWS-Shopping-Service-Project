package com.epam.customerservice.controller;

import com.epam.customerservice.dto.ProductEntity;
import com.epam.customerservice.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private static Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductEntity findOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @GetMapping
    public List<ProductEntity> findByParameters(
            @RequestParam (value = "name", required = false) String name,
            @RequestParam (value = "price",required = false)  String type,
            @RequestParam (value = "manufacturer", required = false) String manufacturer,
            @RequestParam (value = "sorting_price_mode", required = false) String sortingPriceMode
            ){
        return productService.findByParameters(name,type,manufacturer,sortingPriceMode);
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable("id") long id) {

    }
}
