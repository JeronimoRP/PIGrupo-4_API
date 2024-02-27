package es.grupo4.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.Dto.ComentarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import es.grupo4.apirest.model.Comentario;
import es.grupo4.apirest.service.ComentarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/coment")
public class ComentarioController {
	@Autowired
	ComentarioService comentarioService;

	@GetMapping("/comentarios")
	public List<Comentario> getComentarios() {
		return this.comentarioService.getComentarios();
	}

	@PostMapping("/save")
	public void saveComentario(@RequestBody ComentarioDto dto) {
		comentarioService.saveComentario(dto);
	}

	@PostMapping("/subirArchivo")
	public ResponseEntity<String> subirArchivo(@RequestParam("ubicacionArchivo") String ubicacionArchivo,
											   @RequestParam("extension") String extension,
											   @RequestParam("id") int id) {
		try {
			byte[] archivoBytes = Files.readAllBytes(Paths.get(ubicacionArchivo));
			byte[] archivoDecodificado = Base64.getDecoder().decode(archivoBytes);

			String nombreDirectorio = "comentarios";
			String rutaDirectorio = System.getProperty("user.dir") + File.separator + nombreDirectorio;
			String nombreArchivo = "comentario" + id + "." + extension;
			Path rutaArchivo = Paths.get(rutaDirectorio, nombreArchivo);
			Files.write(rutaArchivo, archivoDecodificado);

			Optional<Comentario> optionalComentario = comentarioService.getById(id);
			if (optionalComentario.isPresent()) {
				Comentario comentario = optionalComentario.get();
				comentario.setAdjuntoUrl(rutaArchivo.toString());
				comentarioService.saveComentario(comentario);
				return ResponseEntity.ok("Archivo subido exitosamente con nombre: " + nombreArchivo);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo.");
		}
	}

	@GetMapping("/descargarArchivoBase64")
	public ResponseEntity<byte[]> descargarArchivoBase64(@RequestParam("id") int id) {
		Optional<Comentario> optionalComentario = comentarioService.getById(id);
		if (optionalComentario.isPresent()) {
			Comentario comentario = optionalComentario.get();
			String ubicacionArchivo = comentario.getAdjuntoUrl();
			String extension = ubicacionArchivo.substring(ubicacionArchivo.lastIndexOf(".") + 1);
			try {
				byte[] archivoBytes = Files.readAllBytes(Paths.get(ubicacionArchivo));
				byte[] archivoCodificado = Base64.getEncoder().encode(archivoBytes);
				byte[] extensionBytes = extension.getBytes();
				byte[] contenidoFinal = new byte[archivoCodificado.length + 1 + extensionBytes.length];
				System.arraycopy(archivoCodificado, 0, contenidoFinal, 0, archivoCodificado.length);
				contenidoFinal[archivoCodificado.length] = '.';
				System.arraycopy(extensionBytes, 0, contenidoFinal, archivoCodificado.length + 1, extensionBytes.length);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);
				headers.setContentDisposition(ContentDisposition.builder("attachment").filename("comentarioCodificado.txt").build());
				return ResponseEntity.ok().headers(headers).body(contenidoFinal);
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/comentario/{id}")
	public Optional<Comentario> getComentarioById(@PathVariable("id") int id){
		return this.comentarioService.getById(id);
	}

	@PutMapping(path = "/update/{id}")
	public void updateById(@RequestBody ComentarioDto dto) {
		comentarioService.updateById(dto);
	}

	@DeleteMapping(path = "/delete/{id}")
	public String deleteComentarioById(@PathVariable("id") int id) {
		boolean deleted = comentarioService.deletedComentario(id);
		if (deleted) {
			return "Comentario con id " + id + " eliminado correctamente";
		}else {
			return "Error al eliminar comentario";
		}
	}

	@GetMapping("/comentarios-por-incidencia")
	public List<Comentario> getComentariosByIncidencia(@RequestParam("incidencia") String incidenciaStr) {
		try {
			int incidencia = Integer.parseInt(incidenciaStr);
			return this.comentarioService.getComentariosByIncidencia(incidencia);
		} catch (NumberFormatException e) {
			return Collections.emptyList();
		}
	}


	
}
