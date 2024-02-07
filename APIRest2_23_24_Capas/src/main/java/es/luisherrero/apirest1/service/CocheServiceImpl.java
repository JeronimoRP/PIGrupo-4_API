package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Coche;
import es.luisherrero.apirest1.repository.CocheRepository;
@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheRepository repository;
	
	@Override
	public List<Coche> findAll() {
		return this.repository.findAll();
	}

	@Override
	public List<Coche> findAllByMarca(String marca) {
		Objects.requireNonNull(marca);
        return this.repository.findAllByMarca(marca);
	}

	@Override
	public Optional<Coche> findById(Integer id) {
		return this.repository.findById(id);
	}

	@Override
	public Coche save(Coche coche) {
		this.repository.save(coche);
		return coche;
	}

	@Override
	public Coche update(Coche coche, Integer id) {
		if(repository.existsById(id))
		{
			return repository.save(coche);
		}
		else
		{
			return null;
		}
	}
	
	
	
	@Override
	public void incPrecio(Integer id, Integer increment) {
//		if(repository.existsById(id))
//		{
			repository.incCochePrecio(id, increment);
//			return repository.findById(id).get();
//		}
//		else
//		{
//			return null;
//		} 
	}

	@Override
	public Coche deleteById(Integer id) {
		if(repository.existsById(id))
		{
			Coche coche = repository.findById(id).get();
			repository.delete(coche);
			return coche;
		}
		else
		{
			return null;
		}

	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	

}
