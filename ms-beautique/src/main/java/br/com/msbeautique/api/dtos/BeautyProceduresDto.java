package br.com.msbeautique.api.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.BeautyProceduresEntity}
 */
@Builder
public record BeautyProceduresDto(long id,
                                  String name,
                                  String description, BigDecimal price) implements Serializable {

  @Serial
  private static final long serialVersionUID = -2278840657729014667L;
}
