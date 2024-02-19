package es.grupo4.apirest.controller;

import java.util.List;

import es.grupo4.apirest.Dto.IncidenciaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.service.IncidenciaService;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

	@Autowired
	private IncidenciaService incidenciaService;
	
	@GetMapping("/getincidencias")
	@Description("Método para obtener todas las incidencias")
	public List<IncidenciaDto> getIncidencias(){
		return incidenciaService.getIncidencias();
	}
	
	@PostMapping("/saveIncidencia")
	@Description("Método para guardar una incidencia")
	public void saveIncidencia(@RequestBody IncidenciaDto dto) {
	    incidenciaService.saveIncidencia(dto);
	}
	
	@PutMapping("/updateIncidencia")
	public void updateIncidenciaById(@RequestBody IncidenciaDto dto) {
		incidenciaService.updateById(dto);
	}
	
	@PostMapping("/asignarincidencia")
    public String asignarIncidencia(@RequestParam int idUsuario,@RequestParam int idIncidencia) {

        incidenciaService.asignarIncidencia(idUsuario, idIncidencia);

        return "Incidencia asignada correctamente al usuario con ID: " + idUsuario;
    }
	
	@DeleteMapping("deleteIncidencia/{id}")
	public String deleteIncidenciaById(@PathVariable("id") int id) {
		boolean deleted = incidenciaService.deletedIncidencia(id);
		if (deleted) {
			return "Incidencia con id " + id + "eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}

	@GetMapping("profesorCreador/{idProfesor}")
	public List<IncidenciaDto> getIncidenciasByProfesor(@PathVariable("idProfesor")int id){
		return null;
	}
}
