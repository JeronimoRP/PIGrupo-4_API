package es.luisherrero.apirest1.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Coche;
import es.luisherrero.apirest1.repository.CocheRepository;
import es.luisherrero.apirest1.service.CocheService;
import es.luisherrero.apirest1.service.CocheServiceImpl;

@RestController
@RequestMapping("/api")
public class CocheController {
	@Autowired
	private CocheServiceImpl service;

	@GetMapping("/coches")
	public List<Coche> obtenerCoches(){
		return service.findAll();
	}
	
	@GetMapping("/coches/marca/{marca}")
	public List<Coche> obtenerCochesPorMarca(@PathVariable String marca){
		return service.findAllByMarca(marca);
	}
	
	@GetMapping("/coches/{id}")
	public Coche obtenerUno(@PathVariable Integer id)
	{
		return service.findById(id).orElse(null);
	}

	
	@PutMapping("/coches/{id}")
	public Coche editarCoche(@RequestBody Coche cocheEdit, @PathVariable Integer id)
	{
		return service.update(cocheEdit,id);
	}

	@PatchMapping("/subePrecioCoche")
	public void subirPrecioCoche(@RequestParam Integer id, @RequestParam Integer incremento)
	{
		service.incPrecio(id,incremento);
	}
	@DeleteMapping("/coches/{id}")
	public Coche borrarCoche(@PathVariable Integer id)
	{
		return service.deleteById(id);
	}
	

}
