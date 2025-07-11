package br.com.beautique.mssync.dtos.beautyprocedures;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "beauty_procedures")
public record BeautyProcedureDto(@Id long id, String name, String description, BigDecimal price) implements
    Serializable {

  @Serial
  private static final long serialVersionUID = 2060817069090332646L;
}
