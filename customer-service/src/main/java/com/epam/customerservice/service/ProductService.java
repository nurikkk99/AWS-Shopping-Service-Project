package com.epam.customerservice.service;

import com.epam.customerservice.dto.GetImageDto;
import com.epam.customerservice.dto.ProductDto;
import com.epam.customerservice.dto.SearchAndFilterRequestDto;
import com.epam.customerservice.entity.ImageEntity;
import com.epam.customerservice.entity.ProductEntity;
import com.epam.customerservice.exception.EntityNotFoundException;
import com.epam.customerservice.helper.Indices;
import com.epam.customerservice.repository.ElasticDatabaseRepository;
import com.epam.customerservice.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private ElasticDatabaseRepository elasticDatabaseRepository;
    private ImageRepository imageRepository;
    private ProductDto productDto;
    private GetImageDto getImageDto;

    public ProductService(
            RestHighLevelClient client, ElasticDatabaseRepository elasticDatabaseRepository,
            ImageRepository imageRepository
    ) {
        this.client = client;
        this.elasticDatabaseRepository = elasticDatabaseRepository;
        productDto = new ProductDto();
        getImageDto = new GetImageDto();
        this.imageRepository = imageRepository;
    }

    public ProductDto findById(final Long id) {
        ProductEntity productEntity = elasticDatabaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " does not exist"));
        return productDto.entityToDto(productEntity);
    }

    public ProductDto save(final ProductDto productEntity) {
        logger.info("Saving product entity");
        return productDto.entityToDto(elasticDatabaseRepository.save(productEntity.dtoToEntity()));
    }

    public void deleteAll() {
        elasticDatabaseRepository.deleteAll();
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

    public void update(ProductDto productDto) {
        logger.info("Updating product entity");
        elasticDatabaseRepository.findById(productDto.getId())
                .ifPresent(x -> elasticDatabaseRepository.save(productDto.dtoToEntity()));
    }

    public void saveImage(GetImageDto imageDto) {
        logger.info("Saving image with id {}", imageDto.getImageId());
        imageRepository.save(imageDto.dtoToEntity());
    }

    public void deleteImage(GetImageDto imageDto) {
        logger.info("Deleting image with id {}", imageDto.getImageId());
        imageRepository.deleteById(imageDto.getImageId());
    }

    public List<GetImageDto> getImagesByGoodId(long goodId) {
        List<ImageEntity> imageList = imageRepository.findAllByGoodId(goodId);
        if(imageList.isEmpty()) {
            throw new EntityNotFoundException("Good entity with id " + goodId + "does not exist");
        }
        return imageList.stream().map(x -> getImageDto.entityToDto(x))
                .collect(Collectors.toList());
    }

    public GetImageDto getImageByImageId(long imageId) {
        ImageEntity imageEntity = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + imageId + " does not exist"));
        return getImageDto.entityToDto(imageEntity);
    }

    public void deleteAllImages() {
        imageRepository.deleteAll();
    }
}
