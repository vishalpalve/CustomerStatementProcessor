package com.rabobank.processor.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerStatementRecord {

	@NotNull
	private Long transactionReference;
	@NotNull
	private Long accountNumber;
	@NotNull
	private BigDecimal startBalance;
	@NotNull	
	private BigDecimal mutation;
	@NotNull	
	private String description;
	@NotNull	
	private BigDecimal endBalance;
	
	public CustomerStatementRecord() {}
	
	public CustomerStatementRecord(Long transactionReference, Long accountNumber, BigDecimal startBalance, String description, BigDecimal endBalance, BigDecimal mutation) {
		this.accountNumber = accountNumber;
		this.transactionReference = transactionReference;
		this.startBalance = startBalance;
		this.description = description;
		this.endBalance = endBalance;
		this.mutation = mutation;
	}
		
}
