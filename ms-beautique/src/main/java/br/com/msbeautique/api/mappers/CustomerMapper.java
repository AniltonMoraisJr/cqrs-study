package br.com.msbeautique.api.mappers;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  CustomerDto toDto(CustomerEntity customerEntity);
  CustomerEntity toEntity(CustomerDto customerDto);
}
