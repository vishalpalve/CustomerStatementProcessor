package com.rabobank.processor.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rabobank.processor.model.CustomerStatementRecord;
import com.rabobank.processor.model.ErrorRecord;
import com.rabobank.processor.model.Response;

@Service
public class CustomerStatementService {

	public Response validateRecords(List<CustomerStatementRecord> records) {
		List<CustomerStatementRecord> processedRecords = new ArrayList<>();
		Set<ErrorRecord> duplicateReference = new HashSet<>();
		Set<ErrorRecord> incorrectBalance = new HashSet<>();
		for (CustomerStatementRecord record : records) {
			if(processedRecords.stream().filter(rec->Long.compare(rec.getTransactionReference(), record.getTransactionReference())==0).count()>0) {
				duplicateReference.add(createErrorRecord(record));
			}
			if(!validateBalance(record)) {
				incorrectBalance.add(createErrorRecord(record));
			}
			processedRecords.add(record);
		}
		return createResponse(duplicateReference, incorrectBalance);
	}
	
	private ErrorRecord createErrorRecord(CustomerStatementRecord record) {
		ErrorRecord rec = new ErrorRecord();
		rec.setReference(record.getTransactionReference());
		rec.setAccountNumber(record.getAccountNumber());
		return rec;
	}
	
	private Response createResponse(Set<ErrorRecord> duplicateReference, Set<ErrorRecord> incorrectBalance) {
		Response response= new Response();
		response.setResult("SUCCESSFUL");
		if(!duplicateReference.isEmpty()) {
			if(incorrectBalance.isEmpty()) {
				response.setResult("DUPLICATE_REFERENCE");
			} else {
				response.setResult("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE");
			}
		} else if(!incorrectBalance.isEmpty()) {
			response.setResult("INCORRECT_END_BALANCE");
		}
		response.getErrorRecords().addAll(duplicateReference);
		response.getErrorRecords().addAll(incorrectBalance);
		return response;
	}
	
	public boolean validateBalance(CustomerStatementRecord record) {
		return record.getStartBalance().add(record.getMutation()).equals(record.getEndBalance());
	}
}
