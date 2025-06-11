package br.com.msbeautique.api.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.AppointmentsEntity}
 */
@Builder
public record CreateAppointmentsDto(LocalDateTime dateTime,
                                    Long customer,
                                    Long beautyProcedure) implements Serializable {

  @Serial
  private static final long serialVersionUID = 3938062716119968483L;

}