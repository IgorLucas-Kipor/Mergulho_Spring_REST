package com.algaworks.igorlog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public Cliente listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(7L);
		cliente1.setNome("Gabriel");
		cliente1.setTelefone("34 74185-2156");
		cliente1.setEmail("bielzinhovilas@gmail.com");
		return cliente1;
	}

}
