package com.epam.adminservice.service;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodDto;
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

    public CreateGoodDto save(final CreateGoodDto createGoodDto) {
        logger.info("Saving good entity with id = {}", createGoodDto.getId());
        return createGoodDto.entityToDto(goodsRepository.save(createGoodDto.dtoToEntity()));
    }

    public void delete(final GoodDto goodDto) {
        logger.info("Deleting good entity with id = {}", goodDto.getId());
        goodsRepository.delete(goodDto.dtoToEntity());
    }
}
