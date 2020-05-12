package com.example.demo.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "get Spring Boot";
	}
	
	/*
	@PostMapping
	public String post() {
		return "Post Spring Boot";
	}
	
	@PutMapping
	public String put() {
		return "put Spring Boot";
	}
	
	@DeleteMapping
	public String delete() {
		return "delete Spring Boot";
	}
	*/
	
}
