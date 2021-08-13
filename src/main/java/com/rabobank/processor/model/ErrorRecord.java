package com.rabobank.processor.model;

import lombok.Data;

@Data
public class ErrorRecord {

	private Long reference;
	private Long accountNumber;
}
