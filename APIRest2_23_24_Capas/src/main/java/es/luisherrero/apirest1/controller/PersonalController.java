package es.luisherrero.apirest1.controller;

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

import es.luisherrero.apirest1.model.Perfile;
import es.luisherrero.apirest1.model.Personal;
import es.luisherrero.apirest1.service.PersonalService;

@RestController
@RequestMapping("/a")
public class PersonalController {

	@Autowired
	private PersonalService personalService;
	
	@GetMapping("/personals")
	public List<Personal> getPersonal(){
		return this.personalService.getPersonals();
	}
	
	@PostMapping
	public Personal savePersonal(@RequestBody Personal personal) {
	    return personalService.savePersonal(personal);
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Personal> getPersonalById(@PathVariable("id") int id){
		return  personalService.getById(id);
	}
	
	 @GetMapping("/perfil")
	 public String obtenerPerfilUsuario(Personal personal) {
	        Optional<Perfile> perfil = personalService.obtenerPerfilUsuario(personal);
	        if (perfil != null) {
	            return perfil.get().getPerfil().toString();
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
