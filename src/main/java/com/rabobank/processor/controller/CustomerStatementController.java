package com.rabobank.processor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.processor.model.CustomerStatementRecord;
import com.rabobank.processor.model.Response;
import com.rabobank.processor.service.CustomerStatementService;

@RestController
@RequestMapping("/")
@Validated
public class CustomerStatementController {
	
	@Autowired
	private CustomerStatementService service;

	
	@PostMapping(path = "/customer-statement", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> validateCustomerStatementRecord(@RequestBody @Valid List<CustomerStatementRecord> records){
		Response response = service.validateRecords(records);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
