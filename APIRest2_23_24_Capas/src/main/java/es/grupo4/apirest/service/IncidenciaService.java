package es.grupo4.apirest.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.grupo4.apirest.Dto.IncidenciaDto;
import es.grupo4.apirest.Dto.IncidenciaFilterDto;
import es.grupo4.apirest.Dto.IncidenciaSubtipoDto;
import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.model.IncidenciasSubtipo;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.repository.EquipoRepository;
import es.grupo4.apirest.repository.IncidenciaRepository;
import es.grupo4.apirest.repository.IncidenciasSubtipoRepository;
import es.grupo4.apirest.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class IncidenciaService {

	@Autowired
	IncidenciaRepository incidenciaRepository;

	@Autowired
	PersonalRepository personalRepository;

	@Autowired
	EquipoRepository equipoRepository;

	@Autowired
	IncidenciasSubtipoRepository incidenciasSubtipoRepository;
	
	@PersistenceContext
    private EntityManager em;

	public List<Incidencia> getIncidencias() {
		return this.incidenciaRepository.findAll();
	}

	 public List<Incidencia> getIncidenciasByCreadorId(int creadorId) {
	        return incidenciaRepository.findByPersonal1Id(creadorId);
	    }
	
	public Incidencia saveIncidencia(Incidencia incidencia) {
		return incidenciaRepository.save(incidencia);
	}

	public Optional<Incidencia> getById(int id) {
		return incidenciaRepository.findById(id);
	}
	
	public List<IncidenciaDto> getByTipoAndIncidenciasSubtipoAndEstado(IncidenciaFilterDto filtro) {
		TypedQuery<Integer> subtipoQuery = em.createQuery("SELECT is.id FROM IncidenciasSubtipo is WHERE is.subtipoNombre = :subtipoNombre", Integer.class);
		subtipoQuery.setParameter("subtipoNombre", filtro.getSubtipoNombre());
		List<Integer> subtipoIds = subtipoQuery.getResultList();

		StringBuilder jpqlBuilder = new StringBuilder("SELECT i FROM Incidencia i WHERE 1 = 1");
		if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
            jpqlBuilder.append(" AND i.tipo = :tipo");
        }

        if (!subtipoIds.isEmpty()) {
            jpqlBuilder.append(" AND i.incidenciasSubtipo.id IN :subtipoIds");
        }

        if (filtro.getEstado() != null && !filtro.getEstado().isEmpty()) {
            jpqlBuilder.append(" AND i.estado = :estado");
        }

        if (filtro.getDate() != null) {
            jpqlBuilder.append(" AND i.fechaCreacion = :fechaCreacion");
        }

        TypedQuery<Incidencia> query = em.createQuery(jpqlBuilder.toString(), Incidencia.class);

        if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
            query.setParameter("tipo", filtro.getTipo());
        }

        if (!subtipoIds.isEmpty()) {
            query.setParameter("subtipoIds", subtipoIds);
        }

        if (filtro.getEstado() != null && !filtro.getEstado().isEmpty()) {
            query.setParameter("estado", filtro.getEstado());
        }

        if (filtro.getDate() != null) {
            query.setParameter("fechaCreacion", filtro.getDate());
        }
        return query.getResultList().stream().map(x->IncidenciaDto.fromEntity(x)).collect(Collectors.toList());
	}
/*
	public List<IncidenciaDto> getByTipo(String tipo) {
		return incidenciaRepository.findByTipo(tipo).stream().map(x->IncidenciaDto.fromEntity(x)).collect(Collectors.toList());
	}

	public List<IncidenciaDto> getBySubtipo(int subtipoId) {
		return incidenciaRepository.findByIncidenciasSubtipo_Id(subtipoId).stream().map(x->IncidenciaDto.fromEntity(x)).collect(Collectors.toList());
	}

	public List<Incidencia> getBySubtipoNombre(String subtipoNombre) {
		List<IncidenciasSubtipo> incidenciasSubtipos = incidenciasSubtipoRepository.findBySubtipoNombre(subtipoNombre);

		if (!incidenciasSubtipos.isEmpty()) {
			List<Integer> subtipoIds = incidenciasSubtipos.stream().map(IncidenciasSubtipo::getId)
					.collect(Collectors.toList());

			return incidenciaRepository.findByIncidenciasSubtipo_IdIn(subtipoIds);
		} else {
			return Collections.emptyList();
		}
	}

	public List<Incidencia> getByEstado(String estado) {
		return incidenciaRepository.findByEstado(estado);
	}

 */

	public void updateById(IncidenciaDto request, int id) {
		Incidencia incidencia = incidenciaRepository.findById(request.getNum()).get();
		if(request.getDescripcion()!=null){
			incidencia.setDescripcion(request.getDescripcion());
		}
		if(request.getEstado()!=null){
			incidencia.setEstado(request.getEstado());
		}
		if(request.getFechaCierre()!=null){
			incidencia.setFechaCierre(request.getFechaCierre());
		}
		/*
		if(request.getTipo()!=null){
			incidencia.setTipo(request.getTipo());
		}
		if(request.getIncidenciasSubtipo()!=null){
			incidencia.setIncidenciasSubtipo(IncidenciaSubtipoDto.toEntity(request.getIncidenciasSubtipo()));
		}

		 */

	}

	public void asignarIncidencia(int idPersonal, int idIncidencia) {
		Personal personal = personalRepository.findById(idPersonal).orElse(null);
		Incidencia incidencia = incidenciaRepository.findById(idIncidencia).orElse(null);

		if (personal != null && incidencia != null) {
			incidenciaRepository.save(incidencia);
			incidencia.setPersonal1(personal);
		} else {
			throw new IllegalArgumentException("Usuario o incidencia no encontrados");
		}
	}

	public Boolean deletedIncidencia(int id) {
		try {
			incidenciaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
