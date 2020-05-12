package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarrosService {

	@Autowired
	private CarroRepository carroRepository;

	public Iterable<Carro> getCarro() {
		return carroRepository.findAll();
	}

	public Optional<Carro> getCarroId(Long id) {
		return carroRepository.findById(id);
	}

	public Iterable<Carro> getCarroByTipo(String tipo) {

		return carroRepository.findByTipo(tipo);
	}

	public List<Carro> getCarroFake() {

		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "BMW"));
		carros.add(new Carro(3L, "MERCEDES"));

		return carros;
	}

	public Carro insert(Carro carro) {
		return carroRepository.save(carro);
		
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro");
		
		//Buscar o carro no Banco de dados
		Optional<Carro> optional = getCarroId(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id:"+db.getId());
			
			//Atualiza o Carro
			carroRepository.save(db);
			
			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
		
		
		
	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarroId(id);
		if(carro.isPresent()) {
			carroRepository.deleteById(id);
		}
		
	}

}
