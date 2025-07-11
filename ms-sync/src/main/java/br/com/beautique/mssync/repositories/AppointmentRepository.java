package br.com.beautique.mssync.repositories;

import br.com.beautique.mssync.dtos.appointments.FullAppointmentDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<FullAppointmentDto, Long> {

}
