package com.algaworks.igorlog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.igorlog.domain.model.Entrega;
import com.algaworks.igorlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CancelamentoEntregaService {
	
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void cancelar(Long entregaId) {
		
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.cancelar();
		
		entregaRepository.save(entrega);
	}
	

}
