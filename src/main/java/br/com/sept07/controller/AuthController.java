package br.com.sept07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sept07.domain.form.LoginForm;
import br.com.sept07.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody LoginForm loginForm ) {
		System.out.println(loginForm.getLogin());
		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		System.out.println(dadosLogin.getCredentials());
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			System.out.println(token);
			return ResponseEntity.ok().build();
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
