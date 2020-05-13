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

		return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

		/*
		 * mesma coisa List<Carro> carros = carroRepository.findAll(); List<CarroDTO>
		 * list = new ArrayList<>(); for (Carro c : carros) { list.add(new CarroDTO(c));
		 * } return list;
		 */
	}

	public Optional<CarroDTO> getCarroId(Long id) {
		return carroRepository.findById(id).map(CarroDTO::create);
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {

		return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	
	public CarroDTO insert(Carro carro) {
		
		return CarroDTO.create(carroRepository.save(carro));

	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro");

		// Buscar o carro no Banco de dados
		Optional<Carro> optional = carroRepository.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			// Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id:" + db.getId());

			// Atualiza o Carro
			carroRepository.save(db);

			return CarroDTO.create(db);
		} else {
			return null;
			//throw new RuntimeException("Não foi possivel atualizar o registro");
		}

	}

	public void delete(Long id) {
		
		carroRepository.deleteById(id);

	}

}
