package com.epam.adminservice.dto;

public interface GoodDtoMapper<T> {

  T entityToDto(GoodEntity goodEntity);

  GoodEntity dtoToEntity();
}
