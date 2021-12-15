package com.epam.adminservice.dto;

import java.math.BigDecimal;
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
    private BigDecimal price;
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
    public CreateGoodDto entityToDto(GoodEntity goodEntity) {
        CreateGoodDto createGoodDto = new CreateGoodDto();
        createGoodDto.setId(goodEntity.getId());
        createGoodDto.setName(goodEntity.getName());
        Optional.ofNullable(goodEntity.getType()).ifPresent(x -> createGoodDto.setType(GoodsType.valueOf(x)));
        createGoodDto.setPrice(goodEntity.getPrice());
        createGoodDto.setManufacturer(goodEntity.getManufacturer());
        createGoodDto.setReleaseDate(goodEntity.getReleaseDate());
        return createGoodDto;
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
