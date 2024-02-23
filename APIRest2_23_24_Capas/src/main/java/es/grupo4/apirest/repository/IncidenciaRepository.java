package es.grupo4.apirest.repository;

import java.time.LocalDateTime;
import java.util.List;

import es.grupo4.apirest.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer>{
	
	public List<Incidencia> findByTipoAndIncidenciasSubtipo_IdInAndEstadoAndFechaCreacion(String tipo, List<Integer> subtipoIds, String estado, LocalDateTime fechaCreacion);

	List<Incidencia> findByTipo(String tipo);

	List<Incidencia> findByIncidenciasSubtipo_Id(int subtipoId);

	List<Incidencia> findByEstado(String estado);

	List<Incidencia> findByIncidenciasSubtipo_IdIn(List<Integer> subtipoIds);
	
	List<Incidencia> findByPersonal1Id(int personal1Id);
}
