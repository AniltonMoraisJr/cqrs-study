package br.com.msbeautique.api.dtos;

import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.CustomerEntity}
 */
@Builder
public record CustomerDto(long id, String name, String email, String phone) implements
    Serializable {

  @Serial
  private static final long serialVersionUID = 981637847835584116L;
}