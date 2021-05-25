package com.algaworks.igorlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.api.assembler.EntregaAssembler;
import com.algaworks.igorlog.api.model.EntregaOutput;
import com.algaworks.igorlog.api.model.input.EntregaInput;
import com.algaworks.igorlog.domain.model.Entrega;
import com.algaworks.igorlog.domain.repository.EntregaRepository;
import com.algaworks.igorlog.domain.service.CadastroEntregaService;
import com.algaworks.igorlog.domain.service.CancelamentoEntregaService;
import com.algaworks.igorlog.domain.service.FinalizacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private CadastroEntregaService cadastroEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private CancelamentoEntregaService cancelamentoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaOutput solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = cadastroEntregaService.solicitar(novaEntrega);
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaOutput> listar() {
		List<Entrega> lista = entregaRepository.findAll();
		return entregaAssembler.toCollectionModel(lista);
	}
	
	@GetMapping("/{entregaId") 
	public ResponseEntity<EntregaOutput> buscar (@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar (@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
	
	@PutMapping("/{entregaId}/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar (@PathVariable Long entregaId) {
		cancelamentoEntregaService.cancelar(entregaId);
	}
	
}
