package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.mappers.CustomerMapper;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.CustomerService;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerEntityRepository customerEntityRepository;
  private final CustomerMapper customerMapper;

  public CustomerServiceImpl(CustomerEntityRepository customerEntityRepository,
      CustomerMapper customerMapper) {
    this.customerEntityRepository = customerEntityRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public CustomerDto create(CustomerDto customerDTO) {
    CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
    customerEntity = customerEntityRepository.save(customerEntity);
    return customerMapper.toDto(customerEntity);
  }

  @Override
  public void delete(long id) {
    customerEntityRepository.findById(id).ifPresentOrElse(customerEntityRepository::delete,
        NotFoundException::new);
  }
}
