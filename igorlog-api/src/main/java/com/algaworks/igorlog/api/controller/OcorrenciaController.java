package com.algaworks.igorlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.igorlog.api.assembler.OcorrenciaAssembler;
import com.algaworks.igorlog.api.model.OcorrenciaOutput;
import com.algaworks.igorlog.api.model.input.OcorrenciaInput;
import com.algaworks.igorlog.domain.model.Entrega;
import com.algaworks.igorlog.domain.model.Ocorrencia;
import com.algaworks.igorlog.domain.service.BuscaEntregaService;
import com.algaworks.igorlog.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaEntregaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private RegistroOcorrenciaService registroOcorrenciaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaOutput registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaOutput> listar (@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollection(entrega.getOcorrencias());
	}

}
