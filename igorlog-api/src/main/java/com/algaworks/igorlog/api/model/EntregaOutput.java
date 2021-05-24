package com.algaworks.igorlog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.igorlog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaOutput {
	
	private Long id;
	
	private ClienteResumoModel cliente;
	
	private DestinatarioOutput destinatario;
	
	private BigDecimal taxa;
	
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;

}
