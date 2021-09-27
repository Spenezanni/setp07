package br.com.sept07.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sept07.domain.model.User;
import br.com.sept07.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository userRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		String tokenRecovered = recuperarToken(request);
		boolean validated = tokenService.isTokenValid(tokenRecovered);
        if(validated) {
        	authenticaionUser(tokenRecovered);
        }
		filterChain.doFilter(request, response);
			
	}

	private void authenticaionUser(String token) {
		Long idUser = tokenService.getIdUser(token);
		Optional<User> user = userRepository.findById(idUser);
		UsernamePasswordAuthenticationToken authenticaton = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticaton);
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
