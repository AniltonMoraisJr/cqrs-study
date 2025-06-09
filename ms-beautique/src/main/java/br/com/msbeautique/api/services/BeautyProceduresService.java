package br.com.msbeautique.api.services;

import br.com.msbeautique.api.dtos.BeautyProceduresDto;

public interface BeautyProceduresService {
  BeautyProceduresDto create(BeautyProceduresDto beautyProcedureDto);
  BeautyProceduresDto update(Long id, BeautyProceduresDto beautyProcedureDto);
  void delete(long id);
}
