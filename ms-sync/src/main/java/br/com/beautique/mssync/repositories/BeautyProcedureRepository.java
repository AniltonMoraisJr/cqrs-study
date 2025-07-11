package br.com.beautique.mssync.repositories;

import br.com.beautique.mssync.dtos.beautyprocedures.BeautyProcedureDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDto, Long> {

}
