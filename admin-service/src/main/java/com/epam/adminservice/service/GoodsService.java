package com.epam.adminservice.service;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.dto.ImageEntity;
import com.epam.adminservice.dto.UpdateGoodDto;
import com.epam.adminservice.exception.EntityNotFoundException;
import com.epam.adminservice.repository.ImageS3Repository;
import com.epam.adminservice.repository.GoodsRepository;
import com.epam.adminservice.repository.ImageSqlRepository;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GoodsService {

    private static Logger logger = LoggerFactory.getLogger(GoodsService.class);

    private GoodsRepository goodsRepository;
    private ImageS3Repository imageS3Repository;
    private ImageSqlRepository imageSqlRepository;
    private GetGoodDto getGoodDto;

    public GoodsService(
            GoodsRepository goodsRepository, ImageS3Repository imageS3Repository, ImageSqlRepository imageSqlRepository
    ) {
        this.goodsRepository = goodsRepository;
        this.imageS3Repository = imageS3Repository;
        this.imageSqlRepository = imageSqlRepository;
        this.getGoodDto = new GetGoodDto();
    }

    public List<GetGoodDto> findAll() {
        return goodsRepository.findAll().stream().map(getGoodDto::entityToDto).collect(Collectors.toList());
    }

    public CreateGoodDto save(CreateGoodDto goodDto) {
        logger.info("Saving good entity");
        return goodDto.entityToDto(goodsRepository.save(goodDto.dtoToEntity()));
    }

    public UpdateGoodDto update(Long id, UpdateGoodDto goodDto) {
        if (findOne(id) == null) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        logger.info("Updating good entity with id = {}", id);
        goodDto.setId(id);
        return goodDto.entityToDto(goodsRepository.save(goodDto.dtoToEntity()));
    }

    public GetGoodDto findOne(Long id) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if (goodEntity == null) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        return getGoodDto.entityToDto(goodEntity);
    }

    public void deleteOne(Long id) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if (goodEntity == null) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        logger.info("Deleting good entity with id = {}", id);
        goodsRepository.deleteById(id);
    }

    public void deleteAll() {
        goodsRepository.deleteAll();
    }

    public List<String> getImage(long id) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if (goodEntity == null) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        return imageSqlRepository.findAllByGoodEntity(goodEntity).stream().map(x -> x.getImageURI())
                .collect(Collectors.toList());
    }

    public String saveImage(long id, MultipartFile file) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if (goodEntity == null) {
            throw new EntityNotFoundException("Entity with id " + id + " does not exist");
        }
        String imageKey = generateImageKey(goodEntity);
        String imageURI;
        try {
            imageURI = imageS3Repository.saveImage(imageKey, file);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageURI(imageURI);
        imageEntity.setGoodEntity(goodEntity);
        logger.info("Saving image entity with id = {}", id);
        imageSqlRepository.save(imageEntity);
        return imageURI;
    }

    private String generateImageKey(GoodEntity goodEntity) {
        int imageNumber = Optional.ofNullable(goodEntity.getImagesList().size()).orElse(0) + 1;
        String newImageKey = goodEntity.getType().toString() + getGoodDto.getId() + imageNumber;
        return newImageKey;
    }
}
