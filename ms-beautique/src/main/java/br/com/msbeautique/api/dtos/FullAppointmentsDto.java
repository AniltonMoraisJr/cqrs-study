package br.com.msbeautique.api.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record FullAppointmentsDto(long id, LocalDateTime dateTime,
                                  boolean appointmentOpen,
                                  CustomerDto customer,
                                  BeautyProceduresDto beautyProcedure) implements Serializable {

  @Serial
  private static final long serialVersionUID = -5334993870202328751L;
}
