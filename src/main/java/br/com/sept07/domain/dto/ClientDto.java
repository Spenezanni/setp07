package br.com.sept07.domain.dto;

import java.util.Optional;

import br.com.sept07.domain.model.Client;
import br.com.sept07.repository.ClientRepository;

public class ClientDto {

	private Long id;
	private String nome;
	private String rg;
	private String cpf;

	public ClientDto() {
	}

	public ClientDto(Long id, String nome, String rg, String cpf) {
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
	}

	public ClientDto(Client client) {
       this.id = client.getId();
       this.nome = client.getNome();
       this.rg = client.getRg();
       this.cpf = client.getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Client converterModel(ClientDto clientDto) {
     Client client = new Client(); 
     client.setNome(clientDto.getNome());
     client.setRg(clientDto.getRg());
     client.setCpf(clientDto.getCpf());
	 return client;
	}

	public Client atualizar(Long id, ClientRepository clientRepository) {
		Optional<Client> client = clientRepository.findById(id);
		this.id = client.get().getId();
		this.nome = client.get().getNome();
		this.rg = client.get().getRg();
		this.cpf = client.get().getCpf();
		return client.get();
	}

}
