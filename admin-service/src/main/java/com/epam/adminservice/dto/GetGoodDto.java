package com.epam.adminservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class GetGoodDto extends GoodDto {

    private String name;
    private GoodsType type;
    private BigDecimal price;
    private String manufacturer;
    private LocalDateTime releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public GetGoodDto entityToDto(GoodEntity goodEntity) {
        GetGoodDto getGoodDto = new GetGoodDto();
        getGoodDto.setId(goodEntity.getId());
        getGoodDto.setName(goodEntity.getName());
        Optional.ofNullable(goodEntity.getType()).ifPresent(x -> getGoodDto.setType(GoodsType.valueOf(x)));
        getGoodDto.setPrice(goodEntity.getPrice());
        getGoodDto.setManufacturer(goodEntity.getManufacturer());
        getGoodDto.setReleaseDate(goodEntity.getReleaseDate());
        return getGoodDto;
    }

    @Override
    public GoodEntity dtoToEntity() {
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(this.getId());
        goodEntity.setName(this.name);
        Optional.ofNullable(this.type).ifPresent(x -> goodEntity.setType(x.toString()));
        goodEntity.setPrice(this.price);
        goodEntity.setManufacturer(this.getManufacturer());
        goodEntity.setReleaseDate(this.releaseDate);
        return goodEntity;
    }
}
