package com.algaworks.igorlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.api.model.DestinatarioOutput;
import com.algaworks.igorlog.api.model.EntregaOutput;
import com.algaworks.igorlog.domain.model.Entrega;
import com.algaworks.igorlog.domain.repository.EntregaRepository;
import com.algaworks.igorlog.domain.service.CadastroEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private CadastroEntregaService cadastroEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return cadastroEntregaService.solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{entregaId") 
	public ResponseEntity<EntregaOutput> buscar (@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> {
					EntregaOutput entregaOutput = new EntregaOutput();
					entregaOutput.setId(entrega.getId());
					entregaOutput.setNomeCliente(entrega.getCliente().getNome());
					entregaOutput.setTaxa(entrega.getTaxa());
					entregaOutput.setStatus(entrega.getStatus());
					entregaOutput.setDataPedido(entrega.getDataPedido());
					entregaOutput.setDataFinalizacao(entrega.getDataFinalização());
					entregaOutput.setDestinatario(new DestinatarioOutput());
					entregaOutput.getDestinatario().setLogradouro(entrega.getDestinario().getLogradouro());
					entregaOutput.getDestinatario().setBairro(entrega.getDestinario().getBairro());
					entregaOutput.getDestinatario().setComplemento(entrega.getDestinario().getComplemento());
					entregaOutput.getDestinatario().setNome(entrega.getDestinario().getNome());
					entregaOutput.getDestinatario().setNumero(entrega.getDestinario().getNumero());
					return ResponseEntity.ok(entregaOutput);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
}
