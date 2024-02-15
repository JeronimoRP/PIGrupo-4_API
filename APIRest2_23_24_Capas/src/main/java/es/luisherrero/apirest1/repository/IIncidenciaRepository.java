package es.luisherrero.apirest1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enums.Tipo;
import es.luisherrero.apirest1.model.Incidencia;

@Repository
public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{

	Optional<Incidencia> findByFiltro(Tipo tipo, String subtipo, String estado);
	
}
