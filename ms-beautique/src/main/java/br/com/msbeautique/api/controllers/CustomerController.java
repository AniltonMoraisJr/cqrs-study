package br.com.msbeautique.api.controllers;

import br.com.msbeautique.api.dtos.CustomerDto;
import br.com.msbeautique.api.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping("/")
  public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.create(customerDto));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerDto> update(@PathVariable long id, @RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.update(id, customerDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    customerService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
