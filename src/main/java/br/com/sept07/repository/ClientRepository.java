package br.com.sept07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sept07.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
