package br.com.msbeautique.api.services;

import br.com.msbeautique.api.dtos.AppointmentsDto;

public interface AppointmentsService {
  AppointmentsDto create(AppointmentsDto appointmentsDto);
  AppointmentsDto update(Long id, AppointmentsDto appointmentsDto);
  void delete(long id);
}
