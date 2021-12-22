package com.epam.customerservice.repository;

import com.epam.customerservice.dto.ProductEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByIdInAndManufacturer(Collection<Long> products, String manufacturer);

    List<ProductEntity> findByIdInAndType(Collection<Long> products, String type);

    List<ProductEntity> findAllById(Collection<Long> products);

}
