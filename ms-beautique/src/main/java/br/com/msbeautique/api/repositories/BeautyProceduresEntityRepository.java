package br.com.msbeautique.api.repositories;

import br.com.msbeautique.api.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProceduresEntityRepository extends JpaRepository<BeautyProceduresEntity, Long>
{

}
