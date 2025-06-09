package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.dtos.BeautyProceduresDto;
import br.com.msbeautique.api.entities.BeautyProceduresEntity;
import br.com.msbeautique.api.mappers.BeautyProceduresMapper;
import br.com.msbeautique.api.repositories.BeautyProceduresEntityRepository;
import br.com.msbeautique.api.services.BeautyProceduresService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class BeautyProceduresServiceImpl implements BeautyProceduresService {
  private final BeautyProceduresMapper beautyProceduresMapper;
  private final BeautyProceduresEntityRepository beautyProceduresEntityRepository;

  public BeautyProceduresServiceImpl(BeautyProceduresMapper beautyProceduresMapper,
      BeautyProceduresEntityRepository beautyProceduresEntityRepository) {
    this.beautyProceduresMapper = beautyProceduresMapper;
    this.beautyProceduresEntityRepository = beautyProceduresEntityRepository;
  }

  @Override
  public BeautyProceduresDto create(BeautyProceduresDto beautyProcedureDto) {
    BeautyProceduresEntity beautyProcedureEntity = beautyProceduresMapper.toEntity(beautyProcedureDto);
    beautyProcedureEntity = beautyProceduresEntityRepository.save(beautyProcedureEntity);
    return this.beautyProceduresMapper.toDto(beautyProcedureEntity);
  }

  @Override
  public BeautyProceduresDto update(Long id, BeautyProceduresDto beautyProcedureDto) {
    final BeautyProceduresEntity beautyProceduresEntityFounded = this.beautyProceduresEntityRepository.findById(
        id).orElseThrow();

    BeautyProceduresEntity updateEntity = this.beautyProceduresMapper.toEntity(beautyProcedureDto);
    updateEntity.setId(id);
    updateEntity.setAppointments(beautyProceduresEntityFounded.getAppointments());
    updateEntity.setCreatedAt(beautyProceduresEntityFounded.getCreatedAt());

    updateEntity = this.beautyProceduresEntityRepository.save(updateEntity);
    return this.beautyProceduresMapper.toDto(updateEntity);
  }

  @Override
  public void delete(long id) {
this.beautyProceduresEntityRepository.findById(id).ifPresentOrElse(beautyProceduresEntityRepository::delete,
    NoSuchElementException::new);
  }
}
