package com.algaworks.igorlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.api.assembler.ClienteAssembler;
import com.algaworks.igorlog.api.model.ClienteOutput;
import com.algaworks.igorlog.api.model.input.ClienteInput;
import com.algaworks.igorlog.domain.model.Cliente;
import com.algaworks.igorlog.domain.repository.ClienteRepository;
import com.algaworks.igorlog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	private ClienteAssembler clienteAssembler;

	@GetMapping()
	public List<ClienteOutput> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clienteAssembler.toCollection(clientes);
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteOutput> buscar(@PathVariable Long clienteId) {

		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteOutput adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente = catalogoClienteService.salvar(cliente);
		return clienteAssembler.toModel(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteOutput> atualizar(@Valid @PathVariable Long clienteId, @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);

		return ResponseEntity.ok(clienteAssembler.toModel(cliente));
	}

	@DeleteMapping("{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}
