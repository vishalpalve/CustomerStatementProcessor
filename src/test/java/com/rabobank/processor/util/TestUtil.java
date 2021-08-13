package com.rabobank.processor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtil {
	
	private static final String INVALID_JSON = "invalidJson.json";
	private static final String VALID_JSON = "validJson.json";
	private static final String DUPLICATE_RECORDS_JSON = "duplicate.json";
	private static final String INCORRECT_BALANCE_JSON = "incorrectBalance.json";
	private static final String DUPLICATE_REF_INCORRECT_BALANCE = "dupAndIncorrectBalance.json";
	
	public static String getInvalidInput() throws IOException {
		return readFile(INVALID_JSON);
	}
	
	public static String getValidJson() throws IOException {
		return readFile(VALID_JSON);
	}
	
	public static String getDuplicateJson() throws IOException {
		return readFile(DUPLICATE_RECORDS_JSON);
	}
	
	public static String getIncorrectBalance() throws IOException {
		return readFile(INCORRECT_BALANCE_JSON);
	}
	
	public static String getDupAndIncorrect() throws IOException {
		return readFile(DUPLICATE_REF_INCORRECT_BALANCE);
	}
	
	
	public static String readFile(String fileName) throws IOException {
		InputStream stream = TestUtil.class.getClassLoader().getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		StringBuilder input = new StringBuilder();
		while((line=br.readLine())!=null) {
			input.append(line);
		}
		return input.toString();
	}
	

}
