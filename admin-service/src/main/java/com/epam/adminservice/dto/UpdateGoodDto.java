package com.epam.adminservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateGoodDto implements GoodDtoMapper<UpdateGoodDto> {
    private Long id;

    @NotNull(message = "Name must be given")
    @NotBlank(message = "Name may not be empty")
    private String name;
    private GoodsType type;

    @NotNull(message = "Price must be given")
    @Min(value = 1, message = "The value can not be less than 1")
    private BigDecimal price;
    private String manufacturer;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public UpdateGoodDto entityToDto(GoodEntity goodEntity) {
        UpdateGoodDto goodDto = new UpdateGoodDto();
        goodDto.setId(goodEntity.getId());
        goodDto.setName(goodEntity.getName());
        Optional.ofNullable(goodEntity.getType()).ifPresent(x -> goodDto.setType(GoodsType.valueOf(x)));
        goodDto.setPrice(goodEntity.getPrice());
        goodDto.setManufacturer(goodEntity.getManufacturer());
        return goodDto;
    }

    @Override
    public GoodEntity dtoToEntity() {
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(this.id);
        goodEntity.setName(this.name);
        Optional.ofNullable(this.type).ifPresent(x -> goodEntity.setType(x.toString()));
        goodEntity.setPrice(this.price);
        goodEntity.setManufacturer(this.getManufacturer());
        return goodEntity;
    }
}
