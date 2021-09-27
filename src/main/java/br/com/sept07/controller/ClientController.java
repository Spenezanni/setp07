package br.com.sept07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sept07.domain.dto.ClientDto;
import br.com.sept07.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping
	public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto){
		clientService.createClient(clientDto);
	 return ResponseEntity.ok().body("Client criado com sucesso");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> listClientbyId(@PathVariable("id") Long id) {
	return new ResponseEntity<>((ClientDto) clientService.findClientById(id), HttpStatus.OK);	
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDto>> listAllClients(){
		List<ClientDto> listClientDto = clientService.listAllClients();
		return new ResponseEntity<>(listClientDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDto> updateClientById(@PathVariable("id") Long id, ClientDto clientDto){
		ClientDto updateClientDto = clientService.updateClientById(id, clientDto);
		return new ResponseEntity<>(updateClientDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClienById(@PathVariable("id") Long id){
		clientService.deleteClientById(id);
		return ResponseEntity.ok().body("Client Deletado com Sucesso");
	}
	
	
}
