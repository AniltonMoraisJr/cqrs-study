package br.com.beautique.mssync.dtos.appointments;

import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import br.com.beautique.mssync.dtos.customers.CustomerDto;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "appointments")
public record FullAppointmentDto(@Id long id, LocalDateTime dateTime, boolean appointmentsOpen,
                                 CustomerDto customer,
                                 BeautyProcedureDto beautyProcedure) implements Serializable {

  @Serial
  private static final long serialVersionUID = 3652001369229044359L;
}
