package com.example.demo.api;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Iterable<Carro> get() {
		return carrosService.getCarro();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return carrosService.getCarroId(id);
	}
	
	

	
}