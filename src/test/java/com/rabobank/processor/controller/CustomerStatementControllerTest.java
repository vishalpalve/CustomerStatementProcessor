package com.rabobank.processor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rabobank.processor.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerStatementControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	/**
	 * When body of post request is empty
	 * @throws Exception
	 */
	@Test
	void whenBlankBody_thenReturns400() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());

	}
	
	/**
	 * When invalid json format - fields are missing
	 * @throws Exception
	 */
	@Test
	void whenInValidInput_thenReturns400() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON).content(TestUtil.getInvalidInput()))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result").value("BAD_REQUEST"));

	}
	
	/**
	 * When 
	 * @throws Exception
	 */
	@Test
	void whenDuplicateReference_thenReturns200() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON).content(TestUtil.getDuplicateJson()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("DUPLICATE_REFERENCE"));
	}
	
	/**
	 * When Incorrect Balance then return 200 and result as INCORRECT_END_BALANCE
	 * @throws Exception
	 */
	@Test
	void whenIncorrectBalance_thenReturns200() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON).content(TestUtil.getIncorrectBalance()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("INCORRECT_END_BALANCE"));
	}
	
	/**
	 * When duplicate reference and incorrect balance - then should return 200 and 
	 * result as DUPLICATE_REFERENCE_INCORRECT_END_BALANCE
	 * @throws Exception
	 */
	@Test
	void whenDuplicateRefAndIncorrectBal_thenReturns200() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON).content(TestUtil.getDupAndIncorrect()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE"));
	}
	
	/**
	 * When valid input - it should return 200 and result as SUCCESSFUL
	 * @throws Exception
	 */
	@Test
	void whenValidInput_thenReturns200() throws Exception {
		mockMvc.perform(post("/customer-statement")
				.contentType(MediaType.APPLICATION_JSON).content(TestUtil.getValidJson()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value("SUCCESSFUL"));
	}
}
