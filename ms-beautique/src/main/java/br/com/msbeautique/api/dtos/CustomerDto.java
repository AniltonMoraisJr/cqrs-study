package br.com.msbeautique.api.dtos;

import lombok.Builder;

/**
 * DTO for {@link br.com.msbeautique.api.entities.CustomerEntity}
 */
@Builder
public record CustomerDto(long id, String name, String email, String phone)  {
}