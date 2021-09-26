package br.com.sept07.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sept07.domain.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody LoginForm loginForm ) {
		System.out.println(loginForm.getLogin() + " " + loginForm.getSenha());
		return ResponseEntity.ok().build();
	}

}
