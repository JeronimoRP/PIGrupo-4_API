package es.grupo4.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo4.apirest.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {

	
}
