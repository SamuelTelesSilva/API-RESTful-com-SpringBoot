package com.example.demo.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Carro;
import com.example.demo.domain.CarrosService;
import com.example.demo.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarrosService carrosService;

	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(carrosService.getCarro());
		// return new ResponseEntity <>(carrosService.getCarro(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		CarroDTO carro = carrosService.getCarroId(id);

		return ResponseEntity.ok(carro);

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = carrosService.getCarroByTipo(tipo);

		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Carro carro) {

		CarroDTO c = carrosService.insert(carro);

		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {

		carro.setId(id);

		CarroDTO c = carrosService.update(carro, id);

		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {

		carrosService.delete(id);

		return ResponseEntity.ok().build();

	}

}
