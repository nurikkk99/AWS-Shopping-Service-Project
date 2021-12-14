package com.epam.adminservice.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateGoodDto extends GoodDto{
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
    public UpdateGoodDto entityToDto(GoodEntity goodEntity){
        UpdateGoodDto updateGoodDto = new UpdateGoodDto();
        updateGoodDto.setPrice(goodEntity.getPrice());
        updateGoodDto.setName(goodEntity.getName());
        updateGoodDto.setManufacturer(goodEntity.getManufacturer());
        updateGoodDto.setType(GoodsType.valueOf(goodEntity.getType()));
        updateGoodDto.setReleaseDate(goodEntity.getReleaseDate());
        return updateGoodDto;
    }

    @Override
    public GoodEntity dtoToEntity(){
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setPrice(this.price);
        goodEntity.setName(this.name);
        goodEntity.setManufacturer(this.getManufacturer());
        Optional.ofNullable(this.type).ifPresent(x->goodEntity.setType(x.toString()));
        return goodEntity;
    }
}
