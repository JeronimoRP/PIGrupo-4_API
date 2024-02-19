package es.grupo4.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.service.PersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private PersonalService personalService;
	
	@GetMapping("/getProfesores")
	public List<Personal> getPersonal(){
		return this.personalService.getPersonals();
	}
	
	@PostMapping
	public Personal savePersonal(@RequestBody Personal personal) {
	    return personalService.savePersonal(personal);
	}
	
	@GetMapping("getProfesor/{id}")
	public Optional<Personal> getPersonalById(@PathVariable("id") int id){
		return  personalService.getById(id);
	}
	
	@GetMapping("/perfil")
	public String obtenerPerfilUsuario(Integer id) {
	        String perfil = personalService.obtenerPerfilUsuario(id);

	        if (perfil != null) {
	            return perfil.toLowerCase();
	        } else {
	            return "Usuario no válido";
	        }
	}
	 
	@DeleteMapping(path = "/{id}")
	public String deletePersonalById(@PathVariable("id") int id) {
		boolean deleted = personalService.deletedPersonal(id);
		if (deleted) {
			return "Aula con id " + id + "eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}
}
