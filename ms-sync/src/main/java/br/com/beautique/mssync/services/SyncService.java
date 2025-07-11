package br.com.beautique.mssync.services;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;

public interface SyncService {
  void syncCustomer(CustomerDto customer);
  void syncAppointment(FullAppointmentDto appointment);
  void syncBeautyProcedure(BeautyProcedureDto beautyProcedure);
}
