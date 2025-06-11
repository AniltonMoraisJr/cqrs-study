package br.com.msbeautique.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.mappers.CustomerMapper;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.impl.CustomerServiceImpl;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock
  CustomerEntityRepository customerEntityRepository;
  
  @Mock
  CustomerMapper customerMapper;
  
  @InjectMocks
  CustomerServiceImpl customerService;
  
  CustomerDto mockCustomerDto;

  CustomerEntity mockCustomerEntity;

  @BeforeEach
  public void setUp() {
    mockCustomerDto = CustomerDto.builder()
        .name("Test Customer")
        .email("test@example.com")
        .phone("1234567890")
        .build();

    mockCustomerEntity = CustomerEntity.builder()
        .name("Test Customer")
        .email("test@example.com")
        .phone("1234567890")
        .build();
    mockCustomerEntity.setId(1L);

  }
  
  @Test
  public void testCreateCustomer_withValidCustomerDto_createCustomer(){
    // Arrange
    CustomerDto expectedCustomerDto = CustomerDto.builder()
            .name(mockCustomerDto.name())
                .email(mockCustomerDto.email())
                    .phone(mockCustomerDto.phone())
                        .id(1L).build();

    when(customerMapper.toEntity(mockCustomerDto)).thenReturn(mockCustomerEntity);
    when(customerEntityRepository.save(mockCustomerEntity)).thenReturn(mockCustomerEntity);
    when(customerMapper.toDto(mockCustomerEntity)).thenReturn(expectedCustomerDto);

    // Act
    CustomerDto result = this.customerService.create(mockCustomerDto);

    // Assert
    assertThat(result).isNotNull();
    verify(customerEntityRepository, times(1)).save(mockCustomerEntity);
    assertThat(result.id()).isEqualTo(mockCustomerEntity.getId());
    
  }

  @Test
  public void testUpdateCustomer_withValidCustomerDto_updateCustomer(){
    // Arrange
    final String newEmail = "test2@mail.com";
    final LocalDateTime createdAt = LocalDateTime.of(2025, Month.JUNE, 1, 12, 0);

    CustomerDto updateCustomerDto = CustomerDto.builder()
        .name("Test Customer")
        .email(newEmail)
        .phone("1234567890")
        .build();

    CustomerDto expectedCustomerDto = CustomerDto.builder()
        .name(mockCustomerDto.name())
        .email(newEmail)
        .phone(mockCustomerDto.phone())
        .id(1L).build();

    when(customerEntityRepository.findById(1L)).thenReturn(Optional.of(mockCustomerEntity));

    mockCustomerEntity.setEmail(newEmail);
    mockCustomerEntity.setCreatedAt(createdAt);

    when(customerMapper.toEntity(updateCustomerDto)).thenReturn(mockCustomerEntity);
    when(customerEntityRepository.save(mockCustomerEntity)).thenReturn(mockCustomerEntity);
    when(customerMapper.toDto(mockCustomerEntity)).thenReturn(expectedCustomerDto);

    // Act
    CustomerDto result = this.customerService.update(1L, updateCustomerDto);

    // Assert
    assertThat(result).isNotNull();
    verify(customerEntityRepository, times(1)).save(mockCustomerEntity);
    assertThat(result.id()).isEqualTo(mockCustomerEntity.getId());
    assertThat(result.email()).isEqualTo(newEmail);

  }

  @Test
  void testDeleteCustomer_withValidId_deleteCustomer(){
    // Arrange
    when(customerEntityRepository.findById(1L)).thenReturn(Optional.of(mockCustomerEntity));
    // Act
    this.customerService.delete(1L);
    // Assert
    verify(customerEntityRepository, times(1)).delete(any(CustomerEntity.class));
  }
}
