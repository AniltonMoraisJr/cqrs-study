package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.mappers.CustomerMapper;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.BrokerService;
import br.com.msbeautique.api.services.CustomerService;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerEntityRepository customerEntityRepository;
  private final CustomerMapper customerMapper;
  private final BrokerService brokerService;

  public CustomerServiceImpl(CustomerEntityRepository customerEntityRepository,
      CustomerMapper customerMapper, BrokerService brokerService) {
    this.customerEntityRepository = customerEntityRepository;
    this.customerMapper = customerMapper;
    this.brokerService = brokerService;
  }

  @Override
  public CustomerDto create(CustomerDto customerDTO) {
    CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
    customerEntity = customerEntityRepository.save(customerEntity);

    sendCustomerToQueue(customerEntity);
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

    sendCustomerToQueue(savedEntity);
    return this.customerMapper.toDto(savedEntity);
  }

  @Override
  public void delete(long id) {
    customerEntityRepository.findById(id).ifPresentOrElse(customerEntityRepository::delete,
        NotFoundException::new);
  }

  private void sendCustomerToQueue(CustomerEntity customerEntity) {
    final CustomerDto customerDto = this.customerMapper.toDto(customerEntity);
    this.brokerService.send("customer", customerDto);
  }
}
