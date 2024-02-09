package es.luisherrero.apirest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.Equipo;

@Repository
public interface IEquipoRepository extends JpaRepository<Equipo, Integer>{

	
	
}
