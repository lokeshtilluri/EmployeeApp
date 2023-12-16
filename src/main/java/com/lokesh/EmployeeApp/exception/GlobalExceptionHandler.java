package com.lokesh.EmployeeApp.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webrequest){
		ErrorMessage error = new ErrorMessage(exception.getMessage(),webrequest.getDescription(false),new Date());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String field = ((FieldError)error).getField();
			String value = error.getDefaultMessage();
			errors.put(field, value);
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
