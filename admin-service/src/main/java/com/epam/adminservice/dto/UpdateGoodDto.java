package com.epam.adminservice.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateGoodDto extends GoodDto {

    private String name;
    private GoodsType type;
    private int price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
    public UpdateGoodDto entityToDto(GoodEntity goodEntity) {
        UpdateGoodDto updateGoodDto = new UpdateGoodDto();
        updateGoodDto.setId(goodEntity.getId());
        updateGoodDto.setName(goodEntity.getName());
        Optional.ofNullable(goodEntity.getType()).ifPresent(x -> updateGoodDto.setType(GoodsType.valueOf(x)));
        updateGoodDto.setPrice(goodEntity.getPrice());
        updateGoodDto.setManufacturer(goodEntity.getManufacturer());
        updateGoodDto.setReleaseDate(goodEntity.getReleaseDate());
        return updateGoodDto;
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
