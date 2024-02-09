package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAulaRepository extends JpaRepository<Aula, Integer> {

	
}
