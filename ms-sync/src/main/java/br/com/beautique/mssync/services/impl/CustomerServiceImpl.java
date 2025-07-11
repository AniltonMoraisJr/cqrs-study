package br.com.beautique.mssync.services.impl;

import br.com.beautique.mssync.dtos.customers.CustomerDto;
import br.com.beautique.mssync.repositories.CustomerRepository;
import br.com.beautique.mssync.services.CustomerService;
import br.com.beautique.mssync.utils.SyncLogger;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public void saveCustomer(CustomerDto customer) {
    try {
      SyncLogger.info("Saving customer: {}", customer.toString());
      customerRepository.save(customer);
    } catch (Exception e) {
      SyncLogger.error("Error while saving customer: {}", e.getMessage());
      SyncLogger.trace(Arrays.toString(e.getStackTrace()));
    }
  }
}
