package br.com.msbeautique.api.mappers;

import static java.util.Objects.nonNull;

import br.com.msbeautique.api.dtos.AppointmentsDto;
import br.com.msbeautique.api.dtos.BeautyProceduresDto;
import br.com.msbeautique.api.dtos.CreateAppointmentsDto;
import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.dtos.FullAppointmentsDto;
import br.com.msbeautique.api.dtos.UpdateAppointmentsDto;
import br.com.msbeautique.api.entities.AppointmentsEntity;
import br.com.msbeautique.api.entities.BeautyProceduresEntity;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.repositories.BeautyProceduresEntityRepository;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, BeautyProceduresMapper.class})
@Component
public class AppointmentsMapper {

  @Autowired
  private CustomerMapper customerMapper;
  @Autowired
  private CustomerEntityRepository customerEntityRepository;
  @Autowired
  private BeautyProceduresMapper beautyProceduresMapper;
  @Autowired
  private BeautyProceduresEntityRepository beautyProceduresEntityRepository;

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

  public FullAppointmentsDto toFullDto(AppointmentsEntity appointmentsEntity) {
    CustomerDto customerDto;
    if(nonNull(appointmentsEntity.getCustomer())){
      final CustomerEntity customer = this.customerEntityRepository.findById(appointmentsEntity.getCustomer().getId()).orElse(null);
      customerDto =  this.customerMapper.toDto(customer);
    } else {
      customerDto = null;
    }
    BeautyProceduresDto beautyProcedureDto;
    if(nonNull(appointmentsEntity.getBeautyProcedure())){
      final BeautyProceduresEntity beautyProcedure = this.beautyProceduresEntityRepository.findById(appointmentsEntity.getBeautyProcedure().getId()).orElse(null);
      beautyProcedureDto = this.beautyProceduresMapper.toDto(beautyProcedure);
    } else {
      beautyProcedureDto = null;
    }
    return FullAppointmentsDto.builder()
        .id(appointmentsEntity.getId())
        .appointmentOpen(appointmentsEntity.isAppointmentOpen())
        .dateTime(appointmentsEntity.getDateTime())
        .customer(customerDto)
        .beautyProcedure(beautyProcedureDto)
        .build();
  }
}
