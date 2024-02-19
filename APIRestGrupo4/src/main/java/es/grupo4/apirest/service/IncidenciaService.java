package es.grupo4.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.Dto.IncidenciaDto;
import es.grupo4.apirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.model.Personal;

@Service
public class IncidenciaService {

	@Autowired
    IIncidenciaRepository incidenciaRepository;
	
	@Autowired
    IPersonalRepository personalRepository;

	@Autowired
	IEquipoRepository equipoRepository;

	@Autowired
	IncidenciasSubtipoRepository incidenciasSubtipoRepository;
	
	public List<IncidenciaDto> getIncidencias() {
		List<Incidencia> lista=incidenciaRepository.findAll();
		List<IncidenciaDto> listaDto=new ArrayList<IncidenciaDto>();
		for(Incidencia i:lista){
			listaDto.add(IncidenciaDto.fromEntity(i));
		}
		return listaDto;
	}
	
	public void saveIncidencia(IncidenciaDto dto)
	{
		Incidencia incidencia=IncidenciaDto.toEntity(dto);
		if (dto.getEquipo() != null) {
			incidencia.setEquipo(equipoRepository.getEquipoByEtiqueta(dto.getEquipo()).orElse(null));
		}
		if (dto.getIncidenciasSubtipo() != null) {
			incidencia.setIncidenciasSubtipo(incidenciasSubtipoRepository.getSubtipoByNombre(dto.getIncidenciasSubtipo()).orElse(null));
		}
		incidenciaRepository.save(incidencia);
	}
	
	public Optional<Incidencia> getById(int id) {
		return incidenciaRepository.findById(id);
	}
	
	public void updateById(IncidenciaDto request) {
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
		if(request.getTipo()!=null){
			incidencia.setTipo(request.getTipo());
		}
		if(request.getIncidenciasSubtipo()!=null){
			incidencia.setIncidenciasSubtipo(incidenciasSubtipoRepository.getSubtipoByNombre(request.getIncidenciasSubtipo()).orElse(null));
		}

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
