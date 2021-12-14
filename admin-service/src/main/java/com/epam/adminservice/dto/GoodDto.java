package com.epam.adminservice.dto;

import java.util.Objects;

public abstract class GoodDto {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract GoodDto entityToDto(GoodEntity goodEntity);

    public abstract GoodEntity dtoToEntity();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodDto goodDto = (GoodDto) o;
        return Objects.equals(id, goodDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
