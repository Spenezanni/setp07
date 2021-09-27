package br.com.sept07.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.sept07.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dateExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API do Projeto de Setembro")
				.setSubject(user.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String tokenRecovered) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(tokenRecovered);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		
}
