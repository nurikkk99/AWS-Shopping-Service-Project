package com.epam.adminservice.repository;

import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.dto.ImageEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSqlRepository extends JpaRepository<ImageEntity, Long> {
    Collection<ImageEntity> findAllByGoodEntity(GoodEntity goodEntity);
}
