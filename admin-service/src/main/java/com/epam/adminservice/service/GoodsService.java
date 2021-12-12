package com.epam.adminservice.service;

import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.mapper.GoodEntityToGetGoodDtoMapper;
import com.epam.adminservice.repository.GoodsRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    private GoodsRepository goodsRepository;
    private GoodEntityToGetGoodDtoMapper getGoodMapper;

    public GoodsService(GoodsRepository goodsRepository, GoodEntityToGetGoodDtoMapper getGoodMapper){
        this.goodsRepository = goodsRepository;
        this.getGoodMapper = getGoodMapper;
    }

    public Collection<GetGoodDto> findAll() {
        return goodsRepository.findAll().stream().map(getGoodMapper::entityToDto).collect(Collectors.toList());
    }

    public GoodEntity save(final GoodEntity goodEntity){
        return goodsRepository.save(goodEntity);
    }
}
