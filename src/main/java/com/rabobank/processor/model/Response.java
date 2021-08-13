package com.rabobank.processor.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Response {
	
	private String result;
	private List<ErrorRecord> errorRecords = new ArrayList<>();
}
