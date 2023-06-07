package ca.sunlife.poc.boogle.rest;

import java.util.ArrayList;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.exception.FatalException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler({ Exception.class, FatalException.class })
	public ResponseEntity<Object> defaultExceptionHandler(final Exception exp) {

		return new ResponseEntity<Object>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ BoogleException.class })
	public ResponseEntity<Object> businessExceptionHandler(final BoogleException exp) {
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);

	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class,HttpMessageNotReadableException.class })
	public ResponseEntity<Object> meessageNotReadableExceptionHandler() {
		return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}
}
