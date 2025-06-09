package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.mappers.CustomerMapper;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.CustomerService;
import java.util.NoSuchElementException;
import java.util.Optional;
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
  public CustomerDto update(Long id, CustomerDto customerDTO) {
    final Optional<CustomerEntity> foundCustomer = this.customerEntityRepository.findById(id);
    if(foundCustomer.isEmpty()){
      throw new NoSuchElementException("Customer not found");
    }
    final CustomerEntity entity = customerMapper.toEntity(customerDTO);
    entity.setId(id);
    entity.setAppointments(foundCustomer.get().getAppointments());
    entity.setCreatedAt(foundCustomer.get().getCreatedAt());

    final CustomerEntity savedEntity = this.customerEntityRepository.save(entity);
    return this.customerMapper.toDto(savedEntity);
  }

  @Override
  public void delete(long id) {
    customerEntityRepository.findById(id).ifPresentOrElse(customerEntityRepository::delete,
        NotFoundException::new);
  }
}
