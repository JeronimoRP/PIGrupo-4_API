package es.luisherrero.apirest1.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.Incidencia;

@Repository
public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{
	
	public List<Incidencia> findByTipoAndIncidenciasSubtipo_IdInAndEstadoAndFechaCreacion(String tipo, List<Integer> subtipoIds, String estado, LocalDateTime fechaCreacion);

	List<Incidencia> findByTipo(String tipo);

	List<Incidencia> findByIncidenciasSubtipo_Id(int subtipoId);

	List<Incidencia> findByEstado(String estado);

	List<Incidencia> findByIncidenciasSubtipo_IdIn(List<Integer> subtipoIds);

	List<Incidencia> findByTipoAndIncidenciasSubtipo_IdIn(String tipoFiltrar, List<Integer> subtipoIds);

	List<Incidencia> findByTipoAndEstado(String tipoFiltrar, String estadoFiltrar);

	List<Incidencia> findByIncidenciasSubtipo_IdInAndEstado(List<Integer> subtipoIds, String estadoFiltrar);

	List<Incidencia> findByTipoAndFechaCreacion(String tipo, LocalDateTime fechaCreacion);

	List<Incidencia> findByIncidenciasSubtipo_IdInAndFechaCreacion(List<Integer> subtipoIds, LocalDateTime fechaCreacion);

	List<Incidencia> findByEstadoAndFechaCreacion(String estado, LocalDateTime fechaCreacion);

	List<Incidencia> findByTipoAndIncidenciasSubtipo_IdInAndFechaCreacion(String tipo, List<Integer> subtipoIds, LocalDateTime fechaCreacion);

	List<Incidencia> findByTipoAndEstadoAndFechaCreacion(String tipo, String estado, LocalDateTime fechaCreacion);

	List<Incidencia> findByIncidenciasSubtipo_IdInAndEstadoAndFechaCreacion(List<Integer> subtipoIds, String estado, LocalDateTime fechaCreacion);
}
