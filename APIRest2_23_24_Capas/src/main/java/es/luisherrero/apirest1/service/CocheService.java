package es.luisherrero.apirest1.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import es.luisherrero.apirest1.model.Coche;

public interface CocheService {

	// Obtener datos
    List<Coche> findAll();
    List<Coche> findAllByMarca(String marca);
    Optional<Coche> findById(Integer id);
    
    // Añadir 
    Coche save(Coche coche);
    
    // modificar
    Coche update(Coche coche,Integer id);
    
    // Eliminar
    Coche deleteById(Integer id);
    void deleteAll();

    // Más lógica de negocio:
    // 1. Actualizar precio de coche
    // 2. Coche más caro
    // 3. Media de precios de coches
    // 4. .....
    // asignar url de foto y grabar el fichero subido
    
    void incPrecio(Integer id,Integer increment);
}
