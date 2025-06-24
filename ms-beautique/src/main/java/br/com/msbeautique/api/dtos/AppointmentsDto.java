package br.com.msbeautique.api.dtos;

import java.time.LocalDateTime;
import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.AppointmentsEntity}
 */
@Builder
public record AppointmentsDto(long id, LocalDateTime dateTime,
                              boolean appointmentOpen,
                              Long customer,
                              Long beautyProcedure) {

}