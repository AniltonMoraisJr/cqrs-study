package br.com.msbeautique.api.dtos;

import java.io.Serializable;

/**
 * DTO for {@link br.com.msbeautique.api.entities.CustomerEntity}
 */
public record CustomerDto(long id, String name, String email, String phone) implements
    Serializable {

}