package br.com.beautique.mssync.dtos.appointments;

import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record FullAppointmentDto(long id, LocalDateTime dateTime, boolean appointmentsOpen,
                                 CustomerDto customer,
                                 BeautyProcedureDto beautyProcedure) implements Serializable {

  @Serial
  private static final long serialVersionUID = 3652001369229044359L;
}
