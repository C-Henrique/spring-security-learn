package com.chenrique.spring_secury.demo;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SpringSecurityController {

	/**
	 * Metodo GET - hello-word
	 * 
	 * @return String
	 */
	@GetMapping("/csrf-token")
	public CsrfToken csrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
}
