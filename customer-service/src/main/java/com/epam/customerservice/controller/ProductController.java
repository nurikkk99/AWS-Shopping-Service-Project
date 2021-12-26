package com.epam.customerservice.controller;

import com.epam.customerservice.dto.ProductDto;
import com.epam.customerservice.dto.SearchAndFilterRequestDto;
import com.epam.customerservice.entity.ProductEntity;
import com.epam.customerservice.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto findOne(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/filter")
    public List<ProductDto> searchAndFilter(@RequestBody SearchAndFilterRequestDto requestDto){
        return productService.searchAndFilter(requestDto);
    }

    //TO DELETE
    @PostMapping
    public void save(@RequestBody ProductDto productEntity) {
        productService.save(productEntity);
    }

    //TO DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
