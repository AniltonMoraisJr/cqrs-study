package br.com.beautique.mssync.services.impl;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;
import br.com.beautique.mssync.services.SyncService;
import org.springframework.stereotype.Service;

@Service
public class SyncServiceImpl implements SyncService {

  @Override
  public void syncCustomer(CustomerDto customer) {

  }

  @Override
  public void syncAppointment(FullAppointmentDto appointment) {

  }

  @Override
  public void syncBeautyProcedure(BeautyProcedureDto beautyProcedure) {

  }
}
