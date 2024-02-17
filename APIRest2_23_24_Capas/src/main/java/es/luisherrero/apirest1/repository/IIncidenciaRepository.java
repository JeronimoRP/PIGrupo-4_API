package es.luisherrero.apirest1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.Incidencia;

@Repository
public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{
	
	List<Incidencia> findByTipoAndIncidenciasSubtipo_IdInAndEstado(String tipo, List<Integer> subtipoIds, String estado);
	
	List<Incidencia> findByTipo(String tipo);
	
	List<Incidencia> findByIncidenciasSubtipo_Id(int subtipoId);
	
	List<Incidencia> findByEstado(String estado);
	
	List<Incidencia> findByIncidenciasSubtipo_IdIn(List<Integer> subtipoIds);
}
