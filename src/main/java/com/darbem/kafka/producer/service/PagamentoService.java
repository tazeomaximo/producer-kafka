package com.darbem.kafka.producer.service;

import com.darbem.kafka.producer.dto.PagamentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface PagamentoService {
	
	public void enviarPgtoFila(PagamentoDTO pgto) throws JsonProcessingException;
}
