package br.com.msbeautique.api.services.impl;

import static java.util.Objects.nonNull;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.dtos.CreateAppointmentsDto;
import br.com.msbeautique.api.dtos.UpdateAppointmentsDto;
import br.com.msbeautique.api.entities.AppointmentsEntity;
import br.com.msbeautique.api.mappers.AppointmentsMapper;
import br.com.msbeautique.api.repositories.AppointmentsEntityRepository;
import br.com.msbeautique.api.repositories.BeautyProceduresEntityRepository;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.AppointmentsService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

  private final AppointmentsEntityRepository repository;
  private final CustomerEntityRepository customerEntityRepository;
  private final BeautyProceduresEntityRepository beautyProceduresEntityRepository;
  private final AppointmentsMapper appointmentsMapper;

  public AppointmentsServiceImpl(AppointmentsEntityRepository repository,
      CustomerEntityRepository customerEntityRepository,
      BeautyProceduresEntityRepository beautyProceduresEntityRepository,
      AppointmentsMapper appointmentsMapper) {
    this.repository = repository;
    this.customerEntityRepository = customerEntityRepository;
    this.beautyProceduresEntityRepository = beautyProceduresEntityRepository;
    this.appointmentsMapper = appointmentsMapper;
  }

  @Override
  public AppointmentsDto create(CreateAppointmentsDto appointmentsDto) {
    if(nonNull(appointmentsDto.customer()))
      this.customerEntityRepository.findById(appointmentsDto.customer()).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    if(nonNull(appointmentsDto.beautyProcedure()))
      this.beautyProceduresEntityRepository.findById(appointmentsDto.beautyProcedure()).orElseThrow(() -> new NoSuchElementException("Beauty Procedure not found"));

    AppointmentsEntity entity = appointmentsMapper.toEntity(appointmentsDto);
    if(nonNull(entity.getCustomer()) && nonNull(entity.getBeautyProcedure()))
      entity.setAppointmentOpen(false);
    repository.save(entity);
    return appointmentsMapper.toDto(entity);
  }

  @Override
  public AppointmentsDto update(Long id, UpdateAppointmentsDto appointmentsDto) {
    final AppointmentsEntity foundEntity = this.repository.findById(id).orElseThrow();

    if(nonNull(appointmentsDto.customer()))
      this.customerEntityRepository.findById(appointmentsDto.customer()).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    if(nonNull(appointmentsDto.beautyProcedure()))
      this.beautyProceduresEntityRepository.findById(appointmentsDto.beautyProcedure()).orElseThrow(() -> new NoSuchElementException("Beauty Procedure not found"));

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
