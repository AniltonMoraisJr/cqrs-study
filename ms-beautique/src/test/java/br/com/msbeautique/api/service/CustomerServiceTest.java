package br.com.msbeautique.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.entities.CustomerEntity;
import br.com.msbeautique.api.mappers.CustomerMapper;
import br.com.msbeautique.api.repositories.CustomerEntityRepository;
import br.com.msbeautique.api.services.impl.CustomerServiceImpl;
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
  public void givenValidCustomerDto_createCustomer(){
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
    assertThat(result.id()).isEqualTo(mockCustomerEntity.getId());
    
  }
}
