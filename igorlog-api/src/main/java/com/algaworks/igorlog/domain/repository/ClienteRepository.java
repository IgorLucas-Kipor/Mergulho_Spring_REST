package com.algaworks.igorlog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.algaworks.igorlog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
}
