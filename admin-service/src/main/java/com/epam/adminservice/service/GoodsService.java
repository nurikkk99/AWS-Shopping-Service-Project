package com.epam.adminservice.service;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.dto.UpdateGoodDto;
import com.epam.adminservice.exception.EntityNotFoundException;
import com.epam.adminservice.repository.GoodsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GoodsService {

    private static Logger logger = LoggerFactory.getLogger(GoodsService.class);

    private GoodsRepository goodsRepository;
    private GetGoodDto getGoodDto;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
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
        if(findOne(id) == null) {
            throw new EntityNotFoundException("Entity with id "+id+" does not exist");
        }
        logger.info("Updating good entity with id = {}", id);
        goodDto.setId(id);
        return goodDto.entityToDto(goodsRepository.save(goodDto.dtoToEntity()));
    }

    public GetGoodDto findOne(Long id) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if(goodEntity == null){
            throw new EntityNotFoundException("Entity with id "+id+" does not exist");
        }
        return getGoodDto.entityToDto(goodEntity);
    }

    public void deleteOne(Long id) {
        GoodEntity goodEntity = goodsRepository.getById(id);
        if(goodEntity == null){
            throw new EntityNotFoundException("Entity with id "+id+" does not exist");
        }
        logger.info("Deleting good entity with id = {}", id);
        goodsRepository.deleteById(id);
    }

    public void deleteAll() {
        goodsRepository.deleteAll();
    }
}
