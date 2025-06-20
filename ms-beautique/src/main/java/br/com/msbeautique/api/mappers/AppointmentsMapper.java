package br.com.msbeautique.api.mappers;

import static java.util.Objects.nonNull;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.dtos.CreateAppointmentsDto;
import br.com.msbeautique.api.dtos.UpdateAppointmentsDto;
import br.com.msbeautique.api.entities.AppointmentsEntity;
import br.com.msbeautique.api.entities.BeautyProceduresEntity;
import br.com.msbeautique.api.entities.CustomerEntity;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AppointmentsMapper {

  public AppointmentsEntity toEntity(CreateAppointmentsDto appointmentsDto) {

    final CustomerEntity customerEntity;
    if (nonNull(appointmentsDto.customer())) {
      customerEntity = new CustomerEntity();
      customerEntity.setId(appointmentsDto.customer());
    } else{
      customerEntity = null;
    }

    final BeautyProceduresEntity beautyProcedure;
    if(nonNull(appointmentsDto.beautyProcedure())){
      beautyProcedure = new BeautyProceduresEntity();
      beautyProcedure.setId(appointmentsDto.beautyProcedure());
    } else {
      beautyProcedure = null;
    }

    return AppointmentsEntity.builder()
        .dateTime(appointmentsDto.dateTime())
        .customer(customerEntity)
        .beautyProcedure(beautyProcedure)
        .build();
  }

  public AppointmentsEntity toEntity(UpdateAppointmentsDto appointmentsDto) {

    final CustomerEntity customerEntity;
    if (nonNull(appointmentsDto.customer())) {
      customerEntity = new CustomerEntity();
      customerEntity.setId(appointmentsDto.customer());
    } else{
      customerEntity = null;
    }

    final BeautyProceduresEntity beautyProcedure;
    if(nonNull(appointmentsDto.beautyProcedure())){
      beautyProcedure = new BeautyProceduresEntity();
      beautyProcedure.setId(appointmentsDto.beautyProcedure());
    } else {
      beautyProcedure = null;
    }

    return AppointmentsEntity.builder()
        .dateTime(appointmentsDto.dateTime())
        .customer(customerEntity)
        .beautyProcedure(beautyProcedure)
        .build();
  }

  public AppointmentsDto toDto(AppointmentsEntity appointmentsEntity) {
    return AppointmentsDto.builder()
        .id(appointmentsEntity.getId())
        .appointmentOpen(appointmentsEntity.isAppointmentOpen())
        .dateTime(appointmentsEntity.getDateTime())
        .customer(Optional.ofNullable(appointmentsEntity.getCustomer()).map(CustomerEntity::getId).orElse(null))
        .beautyProcedure(Optional.ofNullable(appointmentsEntity.getBeautyProcedure()).map(BeautyProceduresEntity::getId).orElse(null))
        .build();
  }
}
