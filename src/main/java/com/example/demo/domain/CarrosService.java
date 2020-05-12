package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.domain.dto.CarroDTO;

@Service
public class CarrosService {

	@Autowired
	private CarroRepository carroRepository;

	public List<CarroDTO> getCarro() {

		return carroRepository.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());

		/*
		 * mesma coisa List<Carro> carros = carroRepository.findAll(); List<CarroDTO>
		 * list = new ArrayList<>(); for (Carro c : carros) { list.add(new CarroDTO(c));
		 * } return list;
		 */
	}

	public Optional<Carro> getCarroId(Long id) {
		return carroRepository.findById(id);
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {

		return carroRepository.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
	}

	
	public Carro insert(Carro carro) {
		return carroRepository.save(carro);

	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro");

		// Buscar o carro no Banco de dados
		Optional<Carro> optional = getCarroId(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			// Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id:" + db.getId());

			// Atualiza o Carro
			carroRepository.save(db);

			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}

	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarroId(id);
		if (carro.isPresent()) {
			carroRepository.deleteById(id);
		}

	}

}
