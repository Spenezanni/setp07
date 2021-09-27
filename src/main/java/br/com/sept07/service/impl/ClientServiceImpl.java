package br.com.sept07.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sept07.domain.dto.ClientDto;
import br.com.sept07.domain.model.Client;
import br.com.sept07.repository.ClientRepository;
import br.com.sept07.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void createClient(ClientDto clientDto) {
		Client client = clientDto.converterModel(clientDto);
		clientRepository.save(client);
	}

	@Override
	public ClientDto findClientById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return new ClientDto(client.get());
	}

	@Override
	public List<ClientDto> listAllClients() {
		List<Client> listClient = clientRepository.findAll();
		return listClient.stream().map(ClientDto::new).collect(Collectors.toList());
	}
	
	@Override
	public ClientDto updateClientById(Long id, ClientDto clientDto) {
		Client updateClient = clientDto.atualizar(id, clientRepository);
		clientRepository.save(updateClient);
		return new ClientDto(updateClient);
	}
	
	@Override
	public void deleteClientById(Long id) {
		clientRepository.deleteById(id);
	}

}
