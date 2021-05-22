package com.algaworks.igorlog.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.domain.model.Cliente;
import com.algaworks.igorlog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {
	
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findByNomeContaining("Silva");
	}
	
	//"from Cliente" = me dê todos os objetos da entidade Cliente

}
