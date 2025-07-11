package br.com.beautique.mssync.services;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;

public interface AppointmentService {
  void saveAppointment(FullAppointmentDto appointment);
  void updateAppointmentCustomer(CustomerDto customer);
  void updateAppointmentBeautyProcedures(BeautyProcedureDto beautyProcedure);
}
