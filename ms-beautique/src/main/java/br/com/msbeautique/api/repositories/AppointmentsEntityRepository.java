package br.com.msbeautique.api.repositories;

import br.com.msbeautique.api.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsEntityRepository extends JpaRepository<AppointmentsEntity, Long> {

}
