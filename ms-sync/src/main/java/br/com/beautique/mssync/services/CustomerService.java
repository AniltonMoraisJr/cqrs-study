package br.com.beautique.mssync.services;

import br.com.beautique.mssync.dtos.customers.CustomerDto;

public interface CustomerService {
  void saveCustomer(CustomerDto customer);
}
