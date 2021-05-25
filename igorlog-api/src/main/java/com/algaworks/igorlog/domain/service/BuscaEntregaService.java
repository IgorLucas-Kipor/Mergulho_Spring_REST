package com.algaworks.igorlog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.igorlog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.igorlog.domain.model.Entrega;
import com.algaworks.igorlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
	}

}
