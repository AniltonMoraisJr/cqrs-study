package br.com.beautique.mssync.services.impl;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;
import br.com.beautique.mssync.repositories.AppointmentRepository;
import br.com.beautique.mssync.services.AppointmentService;
import br.com.beautique.mssync.utils.SyncLogger;
import java.util.Arrays;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

  private final AppointmentRepository appointmentRepository;

  private final MongoTemplate mongoTemplate;

  public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
      MongoTemplate mongoTemplate) {
    this.appointmentRepository = appointmentRepository;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void saveAppointment(FullAppointmentDto appointment) {
    try {
      SyncLogger.info("Saving appointment: {}", appointment.toString());
      appointmentRepository.save(appointment);
    } catch (Exception e) {
      SyncLogger.error("Error while saving appointment: {}", e.getMessage());
      SyncLogger.trace(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void updateAppointmentCustomer(CustomerDto customer) {
    try{
      SyncLogger.info("Updating appointment customer: {}", customer.toString());
      Query query = new Query(Criteria.where("customer.id").is(customer.id()));
      Update update = new Update().set("customer", customer);
      mongoTemplate.updateMulti(query, update, FullAppointmentDto.class);
    } catch (Exception e) {
      SyncLogger.error("Error while updating appointment customer: {}", e.getMessage());
      SyncLogger.trace(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void updateAppointmentBeautyProcedures(BeautyProcedureDto beautyProcedure) {
    try{
      SyncLogger.info("Updating appointment beauty procedure: {}", beautyProcedure.toString());
      Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedure.id()));
      Update update = new Update()
          .set("beautyProcedure.name", beautyProcedure.name())
          .set("beautyProcedure.description", beautyProcedure.description());
      mongoTemplate.updateMulti(query, update, FullAppointmentDto.class);
    } catch (Exception e) {
      SyncLogger.error("Error while updating appointment beauty procedure: {}", e.getMessage());
      SyncLogger.trace(Arrays.toString(e.getStackTrace()));
    }
  }
}
