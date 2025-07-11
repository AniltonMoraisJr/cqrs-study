package br.com.beautique.mssync.repositories;

import br.com.beautique.mssync.dtos.customers.CustomerDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDto, Long> {

}
