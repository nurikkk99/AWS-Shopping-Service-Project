package com.epam.adminservice.mapper;

import com.epam.adminservice.dto.GetGoodDto;
import com.epam.adminservice.dto.GoodEntity;
import com.epam.adminservice.dto.GoodsType;
import org.springframework.stereotype.Component;

@Component
public class GoodEntityToGetGoodDtoMapper {

    public GetGoodDto entityToDto(GoodEntity goodEntity) {
        GetGoodDto getGoodDto = new GetGoodDto();
        getGoodDto.setId(goodEntity.getId());
        getGoodDto.setPrice(goodEntity.getPrice());
        getGoodDto.setName(goodEntity.getName());
        getGoodDto.setManufacturer(goodEntity.getManufacturer());
        getGoodDto.setGoodsType(GoodsType.valueOf(goodEntity.getType()));
        getGoodDto.setLocalDateTime(goodEntity.getReleaseDate());
        return getGoodDto;
    }
}
