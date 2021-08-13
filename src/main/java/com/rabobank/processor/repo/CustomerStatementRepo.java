package com.rabobank.processor.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.rabobank.processor.model.CustomerStatementRecord;

public class CustomerStatementRepo {

	private List<CustomerStatementRecord> records;
	
	@PostConstruct
	public void init() {
		records=new ArrayList<>();
		records.add(new CustomerStatementRecord(101L, 1L, BigDecimal.valueOf(25.0) , "transaction 1", BigDecimal.valueOf(30.0), BigDecimal.valueOf(5.0)));
		records.add(new CustomerStatementRecord(102L, 1L, BigDecimal.valueOf(30.0) , "transaction 2", BigDecimal.valueOf(35.0), BigDecimal.valueOf(5.0)));
		records.add(new CustomerStatementRecord(103L, 1L, BigDecimal.valueOf(35.0) , "transaction 3", BigDecimal.valueOf(40.0), BigDecimal.valueOf(5.0)));
		records.add(new CustomerStatementRecord(104L, 1L, BigDecimal.valueOf(40.0) , "transaction 4", BigDecimal.valueOf(45.0), BigDecimal.valueOf(5.0)));
		records.add(new CustomerStatementRecord(106L, 2L, BigDecimal.valueOf(68.0) , "transaction 5", BigDecimal.valueOf(92.0), BigDecimal.valueOf(24.0)));
		records.add(new CustomerStatementRecord(105L, 2L, BigDecimal.valueOf(92.0) , "transaction 6", BigDecimal.valueOf(95.0), BigDecimal.valueOf(3.0)));
		records.add(new CustomerStatementRecord(107L, 2L, BigDecimal.valueOf(95.0) , "transaction 7", BigDecimal.valueOf(90.0), BigDecimal.valueOf(-5.0)));
	}
	
	public void save(CustomerStatementRecord record) {
		records.add(record);
	}
	
	public boolean validateTransactionReference(CustomerStatementRecord record) {
		Long count = records.stream().filter(rec->Long.compare(rec.getTransactionReference(),record.getTransactionReference())==0).count();
		return count>0;
	}
}
