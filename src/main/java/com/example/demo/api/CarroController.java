package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Carro;
import com.example.demo.domain.CarrosService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarrosService carrosService;

	@GetMapping
	public ResponseEntity <Iterable<Carro>> get() {
		return ResponseEntity.ok(carrosService.getCarro());
		//return new ResponseEntity <>(carrosService.getCarro(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Carro> carro = carrosService.getCarroId(id);

		//lambdas
		return carro
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		
		/*
		
		//if ternario  a sintaxe é: (Expressão) ? ValorTrue : ValorFalse
		return carro.isPresent() ?
				ResponseEntity.ok(carro.get()) :
				ResponseEntity.notFound().build();
		
		Uma forma de fazer o erro 404
		if (carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		}else {
			return ResponseEntity.notFound().build();
		}*/
		
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List <Carro> carros =  carrosService.getCarroByTipo(tipo);
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}

	@PostMapping
	public String post(@RequestBody Carro carro) {

		Carro c = carrosService.insert(carro);

		return "Carro salvo com Sucesso" + c.getId();

	}

	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {

		Carro c = carrosService.update(carro, id);

		return "Carro Atualizado com Sucesso" + c.getId();

	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {

		carrosService.delete(id);

		return "Carro Deletado com Sucesso";

	}

}
