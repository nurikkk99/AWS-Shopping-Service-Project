package com.epam.customerservice.service;

import com.epam.customerservice.dto.ProductDto;
import com.epam.customerservice.dto.SearchAndFilterRequestDto;
import com.epam.customerservice.entity.ProductEntity;
import com.epam.customerservice.exception.EntityNotFoundException;
import com.epam.customerservice.helper.Indices;
import com.epam.customerservice.repository.ElasticDatabaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private RestHighLevelClient client;
    private ElasticDatabaseRepository repository;
    private ProductDto productDto;

    public ProductService(RestHighLevelClient client, ElasticDatabaseRepository repository) {
        this.client = client;
        this.repository = repository;
        productDto = new ProductDto();
    }

    public ProductDto findById(final Long id) {
        ProductEntity productEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " does not exist"));
        return productDto.entityToDto(productEntity);
    }

    public ProductDto save(final ProductDto productEntity) {
        logger.info("Saving product entity");
        return productDto.entityToDto(repository.save(productEntity.dtoToEntity()));
    }

    public void deleteById(Long id) {
        ProductEntity productEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " does not exist"));
        logger.info("Deleting product entity");
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<ProductDto> searchAndFilter(SearchAndFilterRequestDto requestDto) {
        SearchRequest request = SearchAndFilterRequestBuilder.buildSearchAndFilterRequest(
                Indices.PRODUCT_INDEX, requestDto);

        SearchResponse response;
        try {
            response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] searchHits = response.getHits().getHits();
            List<ProductDto> products = new ArrayList<>(searchHits.length);

            for (SearchHit hit : searchHits) {
                products.add(objectMapper.readValue(hit.getSourceAsString(), ProductDto.class));
            }
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
