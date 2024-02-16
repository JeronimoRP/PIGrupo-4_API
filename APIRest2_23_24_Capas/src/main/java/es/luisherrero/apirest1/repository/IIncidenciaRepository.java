package es.luisherrero.apirest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enums.Tipo;
import es.luisherrero.apirest1.model.Incidencia;
import es.luisherrero.apirest1.model.IncidenciasSubtipo;

@Repository
public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{

	Incidencia findByTipoAndIncidenciasSubtipoAndEstado(Tipo tipo, IncidenciasSubtipo incidenciasSubtipo, String estado);
	
}
