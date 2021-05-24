package com.algaworks.igorlog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.igorlog.api.model.EntregaOutput;
import com.algaworks.igorlog.api.model.input.EntregaInput;
import com.algaworks.igorlog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	public EntregaOutput toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaOutput.class);
	}
	
	public List<EntregaOutput> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream().
				map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
	
}
