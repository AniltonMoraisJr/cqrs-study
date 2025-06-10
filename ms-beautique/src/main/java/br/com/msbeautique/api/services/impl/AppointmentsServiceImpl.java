package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.entities.AppointmentsEntity;
import br.com.msbeautique.api.mappers.AppointmentsMapper;
import br.com.msbeautique.api.repositories.AppointmentsEntityRepository;
import br.com.msbeautique.api.services.AppointmentsService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

  private final AppointmentsEntityRepository repository;
  private final AppointmentsMapper appointmentsMapper;

  public AppointmentsServiceImpl(AppointmentsEntityRepository repository,
      AppointmentsMapper appointmentsMapper) {
    this.repository = repository;
    this.appointmentsMapper = appointmentsMapper;
  }

  @Override
  public AppointmentsDto create(AppointmentsDto appointmentsDto) {
    AppointmentsEntity entity = appointmentsMapper.toEntity(appointmentsDto);
    repository.save(entity);
    return appointmentsMapper.toDto(entity);
  }

  @Override
  public AppointmentsDto update(Long id, AppointmentsDto appointmentsDto) {
    final AppointmentsEntity foundEntity = this.repository.findById(id).orElseThrow();
    final AppointmentsEntity entity = appointmentsMapper.toEntity(appointmentsDto);
    entity.setId(foundEntity.getId());
    entity.setCreatedAt(foundEntity.getCreatedAt());
    repository.save(entity);
    return appointmentsMapper.toDto(entity);
  }

  @Override
  public void delete(long id) {
    this.repository.findById(id).ifPresentOrElse(repository::delete, NoSuchElementException::new);
  }
}
