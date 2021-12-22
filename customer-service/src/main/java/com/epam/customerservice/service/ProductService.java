package com.epam.customerservice.service;

import com.epam.customerservice.dto.ProductEntity;
import com.epam.customerservice.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity findOne(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return productEntity;
    }

    public List<ProductEntity> findByParameters(
            String name, String type, String manufacturer, String sortingPriceMode) {

        List<Long> listOfIds = new ArrayList<>();
        if (name != null) {
            listOfIds = filterByName(name);
        }
        if (type != null) {
            listOfIds = filterByIdsAndType(listOfIds, type);
        }
        if (manufacturer != null) {
            listOfIds = filterByIdsAndManufacturer(listOfIds, manufacturer);
        }
        if (sortingPriceMode != null) {
            listOfIds = sortBySortingPriceMode(listOfIds, sortingPriceMode);
        }
        return productRepository.findAllById(listOfIds);
    }

    private List<Long> sortBySortingPriceMode(List<Long> listOfIds, String sortingPriceMode) {
        Comparator<Long> productsPriceComparator;
        if (sortingPriceMode.equals("ascending")) {
            productsPriceComparator = (o1, o2) -> productRepository.findById(o1).get().getPrice()
                    .subtract(productRepository.findById(o2).get().getPrice()).intValue();
        } else if (sortingPriceMode.equals("descending")) {
            productsPriceComparator = (o1, o2) -> productRepository.findById(o2).get().getPrice()
                    .subtract(productRepository.findById(o1).get().getPrice()).intValue();
        } else {
            throw new RuntimeException("Wrong sortingType");
        }
        listOfIds.sort(productsPriceComparator);
        return listOfIds;
    }

    private List<Long> filterByIdsAndManufacturer(List<Long> oldIds, String manufacturer) {
        List<ProductEntity> productEntities = productRepository.findByIdInAndManufacturer(oldIds, manufacturer);
        return productEntities.stream().map(ProductEntity::getId).collect(Collectors.toList());
    }

    private List<Long> filterByIdsAndType(List<Long> oldIds, String type) {
        List<ProductEntity> productEntities = productRepository.findByIdInAndType(oldIds, type);
        return productEntities.stream().map(ProductEntity::getId).collect(Collectors.toList());
    }

    private List<Long> filterByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByName(name);
        return productEntities.stream().map(ProductEntity::getId).collect(Collectors.toList());
    }
}
