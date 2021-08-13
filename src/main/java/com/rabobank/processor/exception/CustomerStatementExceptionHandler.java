package com.rabobank.processor.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.rabobank.processor.model.Response;

@RestControllerAdvice
public class CustomerStatementExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = ConstraintViolationException.class)
	final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		Response response = new Response();
		response.setResult("BAD_REQUEST");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		Response response = new Response();
		response.setResult("INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(response, headers, status);
	}

	
	
}
