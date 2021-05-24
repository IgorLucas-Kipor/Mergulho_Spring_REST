package com.algaworks.igorlog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.igorlog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Ao ser chamado o Entrega no EntregaController, vai buscar validar por grupo Default
	 * Ao chegar na classe entrega, vai converter isso de Fault para o ClienteId
	 * Ao entrar na classe cliente, validará apenas o cliente, que está no grupo ClienteId
	 * Demais itens não serão validados por estarem no grupo Default
	 * 
	 */
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class
	)
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotNull
	@Valid
	@Embedded
	private Destinatario destinario;
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalização;
}
