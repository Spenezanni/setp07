package br.com.sept07.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String perfil;

	public Perfil() {
	}

	public Perfil(Long id, String perfil) {
		this.id = id;
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String getAuthority() {
		return this.perfil;
	}

}
