package es.grupo4.apirest.controller;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.Dto.PersonalDto;
import es.grupo4.apirest.Dto.PersonalInputDto;
import es.grupo4.apirest.Dto.PersonalOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.service.PersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private PersonalService personalService;

	@GetMapping("/listaPersonal")
	public List<PersonalDto> getPersonal() {
		return this.personalService.getPersonal();
	}

	@PostMapping("/save")
	public void savePersonal(@RequestBody PersonalDto dto) {
		personalService.savePersonal(dto);
	}

	@GetMapping("infoPersonal/{id}")
	public PersonalDto getPersonalById(@PathVariable("id") int id) {
		return personalService.getById(id);
	}
	
	@GetMapping("/nombre/apellidos")
	public Personal getPersonalById(@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "primerApellido") String apellido1,
			@RequestParam(name = "segundoApellido") String apellido2) {
		return personalService.getByNombreApellidos(nombre, apellido1,apellido2);
	}

	@GetMapping("/perfil/{id}")
	public String obtenerPerfilUsuario(Integer idusuario) {
		String nombre = personalService.obtenerPerfilUsuario(idusuario);
		return nombre!=null?nombre:"Usuario no válido";
	}

	@PutMapping(path = "update/{id}")
	public Personal updatePersonalById(@RequestBody Personal personal, @PathVariable int id) {
		return personalService.updateById(personal, id);
	}

	@DeleteMapping(path = "delete/{id}")
	public String deletePersonalById(@PathVariable("id") int id) {
		boolean deleted = personalService.deletedPersonal(id);
		if (deleted) {
			return "Aula con id " + id + "eliminado correctamente";
		} else {
			return "Error al eliminar aula";
		}
	}
	@PostMapping("/login")
	public PersonalOutputDto getIncidenciasUsuario(@RequestBody PersonalInputDto dto){
		return personalService.getIncidenciasUsuario(dto);
	}
}
