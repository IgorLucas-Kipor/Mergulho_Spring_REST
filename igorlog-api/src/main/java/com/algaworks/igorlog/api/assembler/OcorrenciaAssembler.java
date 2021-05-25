package com.algaworks.igorlog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.igorlog.api.model.OcorrenciaOutput;
import com.algaworks.igorlog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaOutput toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaOutput.class);
	}
	
	public List<OcorrenciaOutput> toCollection(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
