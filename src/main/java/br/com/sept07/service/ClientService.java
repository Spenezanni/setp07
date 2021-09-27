package br.com.sept07.service;

import java.util.List;

import br.com.sept07.domain.dto.ClientDto;

public interface ClientService {

	void createClient(ClientDto clientDto);

	ClientDto findClientById(Long id);

	List<ClientDto> listAllClients();

	void deleteClientById(Long id);

	ClientDto updateClientById(Long id, ClientDto clientDto);
	
	

}
