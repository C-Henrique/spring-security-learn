package com.chenrique.spring_secury.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

	/**
	 * Metodo GET - hello-word
	 * 
	 * @return String
	 */
	@GetMapping("/hello-word")
	public String helloWord() {
		return "Ola mundo";
	}
}
