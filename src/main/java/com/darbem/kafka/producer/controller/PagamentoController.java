package com.darbem.kafka.producer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.darbem.kafka.producer.dto.PagamentoDTO;
import com.darbem.kafka.producer.service.PagamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//https://springdoc.org/#migrating-from-springfox
@Tag(name = "Pagamento")
@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService service;

	@Operation(summary = "Post enviar pagamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pagamento Enviado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
		})
	@PostMapping(value = "/",consumes = { MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(code = HttpStatus.CREATED)
	public void efetuarPagamento(@Parameter(name = "pagamento") @RequestBody PagamentoDTO pagamento) throws JsonProcessingException {
		
		service.enviarPgtoFila(pagamento);

	}
}
