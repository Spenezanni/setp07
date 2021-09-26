package br.com.sept07.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sept07.domain.model.User;
import br.com.sept07.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userLogin = userRepository.findByLogin(username);
		if(userLogin.isPresent()) {
			return userLogin.get();
		}
		throw new UsernameNotFoundException("User not found!");
	}

}
