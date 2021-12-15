package com.epam.adminservice.service;

import com.epam.adminservice.dto.CreateGoodDto;
import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodDto;
import com.epam.adminservice.repository.GoodsRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    private GoodsRepository goodsRepository;
    private GetGoodDto getGoodDto;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
        this.getGoodDto = new GetGoodDto();
    }

    public Collection<GetGoodDto> findAll() {
        return goodsRepository.findAll().stream().map(getGoodDto::entityToDto).collect(Collectors.toList());
    }

    public CreateGoodDto save(final CreateGoodDto createGoodDto) {
        return createGoodDto.entityToDto(goodsRepository.save(createGoodDto.dtoToEntity()));
    }

    public void delete(final GoodDto goodDto) {
        goodsRepository.delete(goodDto.dtoToEntity());
    }
}
