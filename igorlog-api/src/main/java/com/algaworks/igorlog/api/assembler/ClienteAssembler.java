package com.algaworks.igorlog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.algaworks.igorlog.api.model.ClienteOutput;
import com.algaworks.igorlog.api.model.input.ClienteInput;
import com.algaworks.igorlog.domain.model.Cliente;

public class ClienteAssembler {
	
	private ModelMapper modelMapper;
	
	public ClienteOutput toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteOutput.class);
	}
	
	public List<ClienteOutput> toCollection(List<Cliente> clientes) {
		return clientes.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

}
