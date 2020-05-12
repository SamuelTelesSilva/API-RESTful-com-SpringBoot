package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
