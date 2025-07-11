package br.com.beautique.mssync.dtos.customers;

import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "customers")
public record CustomerDto(@Id long id, String name, String email, String phone) implements
    Serializable {

  @Serial
  private static final long serialVersionUID = -8723811061832241027L;
}
