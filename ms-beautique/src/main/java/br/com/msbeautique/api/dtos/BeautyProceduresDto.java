package br.com.msbeautique.api.dtos;

import java.math.BigDecimal;
import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.BeautyProceduresEntity}
 */
@Builder
public record BeautyProceduresDto(long id,
                                  String name,
                                  String description, BigDecimal price)  {
}
