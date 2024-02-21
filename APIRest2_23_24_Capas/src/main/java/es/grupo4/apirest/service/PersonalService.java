package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.grupo4.apirest.Dto.IncidenciaDto;
import es.grupo4.apirest.Dto.PersonalInputDto;
import es.grupo4.apirest.Dto.PersonalOutputDto;
import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.repository.IncidenciaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.repository.PerfileRepository;
import es.grupo4.apirest.repository.PersonalRepository;

@Service
public class PersonalService {

	@Autowired
	PersonalRepository personalRepository;

	@Autowired
    PerfileRepository perfileRepository;

	@Autowired
	IncidenciaRepository incidenciaRepository;

	@PersistenceContext
	EntityManager em;

	public List<Personal> getPersonals() {
		return this.personalRepository.findAll();
	}

	public Personal savePersonal(Personal personal) {
		return personalRepository.save(personal);
	}

	public Optional<Personal> getById(int id) {
		return personalRepository.findById(id);
	}
	
	public Personal getByNombreApellidos(String nombre, String apellido1, String apellido2) {
		return personalRepository.findByNombreAndApellido1AndApellido2(nombre,apellido1,apellido2).orElse(null);
	}

	public Personal updateById(Personal request, int id) {
		Personal personal = personalRepository.findById(id).get();
		personal.setActivo(request.getActivo());
		personal.setDepartamento(request.getDepartamento());
		personalRepository.save(personal);
		return personal;
	}
	
	public Boolean deletedPersonal(int id) {
		try {
			personalRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Optional<Perfile> obtenerPerfilUsuario(Personal personal) {
	      return personal != null ? perfileRepository.findById(personal.getId()) : null;
	 }

	public PersonalOutputDto getIncidenciasUsuario(PersonalInputDto dto){
		PersonalOutputDto usuario=this.getUsuario(dto);
		if(usuario!=null){
			usuario.setNombre(this.getNombre(usuario.getPersonalId()));
			List<Incidencia> listaIncidencias=incidenciaRepository.findByPersonal1Id(usuario.getPersonalId());
			usuario.setIncidencias(listaIncidencias.stream().map(x->IncidenciaDto.fromEntity(x)).collect(Collectors.toList()));

		}
		return usuario;
	}

	private PersonalOutputDto getUsuario(PersonalInputDto dto){
		String sql=String.format("select personal_id from perfiles where educantabria='%s' and password='%s'",dto.getEducantabria(),dto.getPassword());
		Integer id =(Integer)em.createQuery(sql).getSingleResult();
		if(id!=null){
			PersonalOutputDto usuario=new PersonalOutputDto();
			usuario.setPersonalId(id);
			return usuario;
		}else return null;

	}
	private String getNombre(Integer id){
		String sql=String.format("select nombre from personal where id=%d",id);
		return (String)em.createQuery(sql).getSingleResult();
	}

}
