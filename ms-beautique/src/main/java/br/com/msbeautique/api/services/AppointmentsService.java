package br.com.msbeautique.api.services;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.dtos.CreateAppointmentsDto;
import br.com.msbeautique.api.dtos.UpdateAppointmentsDto;

public interface AppointmentsService {
  AppointmentsDto create(CreateAppointmentsDto appointmentsDto);
  AppointmentsDto update(Long id, UpdateAppointmentsDto appointmentsDto);
  void delete(long id);
}
