package com.algaworks.igorlog.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	@NotNull
	@Valid
	private Long id;
	
	@NotNull
	@Valid
	private String nome;
	
	@NotNull
	@Valid
	private String email;
	
	@NotNull
	@Valid
	private String telefone;

}
