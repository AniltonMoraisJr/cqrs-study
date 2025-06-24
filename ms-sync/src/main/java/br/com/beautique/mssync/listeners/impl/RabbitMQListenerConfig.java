package br.com.beautique.mssync.listeners.impl;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;
import br.com.beautique.mssync.listeners.ListenerConfig;
import br.com.beautique.mssync.utils.SyncLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQListenerConfig implements ListenerConfig {

  private final ObjectMapper objectMapper;

  public RabbitMQListenerConfig(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @RabbitListener(queues = "customerQueue")
  @Override
  public void listenToCustomerQueue(String message) {
    try {
      final CustomerDto customerDto = objectMapper.readValue(message, CustomerDto.class);
      SyncLogger.info("Customer received from queue customerQueue: {}", customerDto.toString());
    } catch (Exception e) {
      SyncLogger.error("Error while listening to customerQueue: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @RabbitListener(queues = "appointmentQueue")
  @Override
  public void listenToAppointmentQueue(String message) {
    try {
      final FullAppointmentDto fullAppointmentDto = objectMapper.readValue(message,
          FullAppointmentDto.class);
      SyncLogger.info("Appointment received from queue appointmentQueue: {}", fullAppointmentDto.toString());
    } catch (Exception e) {
      SyncLogger.error("Error while listening to appointmentQueue: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @RabbitListener(queues = "beautyProcedureQueue")
  @Override
  public void listenToBeautyProcedureQueue(String message) {
    try {
      final BeautyProcedureDto beautyProcedureDto = objectMapper.readValue(message,
          BeautyProcedureDto.class);
      SyncLogger.info("Beauty Procedure received from queue beautyProcedureQueue: {}", beautyProcedureDto.toString());
    } catch (Exception e) {
      SyncLogger.error("Error while listening to beautyProcedureQueue: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
