package br.com.msbeautique.api.mappers;

import br.com.msbeautique.api.dtos.BeautyProceduresDto;
import br.com.msbeautique.api.entities.BeautyProceduresEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeautyProceduresMapper {
  BeautyProceduresDto toDto(BeautyProceduresEntity beautyProcedureEntity);
  BeautyProceduresEntity toEntity(BeautyProceduresDto beautyProcedureDto);
}
