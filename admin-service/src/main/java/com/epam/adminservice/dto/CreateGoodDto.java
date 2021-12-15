package com.epam.adminservice.dto;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateGoodDto extends GoodDto {

    @NotNull
    @NotBlank
    private String name;
    private GoodsType type;

    @NotNull
    @Min(value = 1, message = "The value can not be less than 1")
    private int price;
    private String manufacturer;

    @NotNull
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
    public CreateGoodDto entityToDto(GoodEntity goodEntity) {
        CreateGoodDto createGoodDto = new CreateGoodDto();
        createGoodDto.setId(goodEntity.getId());
        createGoodDto.setPrice(goodEntity.getPrice());
        createGoodDto.setName(goodEntity.getName());
        createGoodDto.setManufacturer(goodEntity.getManufacturer());
        Optional.ofNullable(goodEntity.getType()).ifPresent(x -> createGoodDto.setType(GoodsType.valueOf(x)));
        createGoodDto.setReleaseDate(goodEntity.getReleaseDate());
        return createGoodDto;
    }

    @Override
    public GoodEntity dtoToEntity() {
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(this.getId());
        goodEntity.setPrice(this.price);
        goodEntity.setName(this.name);
        goodEntity.setManufacturer(this.getManufacturer());
        Optional.ofNullable(this.type).ifPresent(x -> goodEntity.setType(x.toString()));
        return goodEntity;
    }
}
