package br.com.msbeautique.api.services;

import br.com.msbeautique.api.dtos.CustomerDto;

public interface CustomerService {
  CustomerDto create(CustomerDto customerDTO);
  CustomerDto update(Long id, CustomerDto customerDTO);
  void delete(long id);
}
