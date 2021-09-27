package br.com.sept07.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;

	public AuthenticationTokenFilter(TokenService tokenService) {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		String tokenRecovered = recuperarToken(request);
		boolean validated = tokenService.isTokenValid(tokenRecovered);
        System.out.println(validated);
		filterChain.doFilter(request, response);
			
	}

	private String recuperarToken(HttpServletRequest request) {
		//faz um get do token no header
		String tokenHeader = request.getHeader("Authorization");
		if(tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")) {
			return null;			
		}
		return tokenHeader.substring(7, tokenHeader.length());
	}

}
