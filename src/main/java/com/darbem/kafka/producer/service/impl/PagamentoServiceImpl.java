package com.darbem.kafka.producer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.darbem.kafka.producer.dto.PagamentoDTO;
import com.darbem.kafka.producer.service.KafkaService;
import com.darbem.kafka.producer.service.PagamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService{
	
	@Autowired
	private KafkaService kafkaService;
	
	@Value(value = "${kafka.topic.name}")
	private String topicName;

	@Override
	public void enviarPgtoFila(PagamentoDTO pgto) throws JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(pgto);
		
		log.info("JSON enviado kafka: {}", json);
		
		kafkaService.send(json, topicName);
	}

}
