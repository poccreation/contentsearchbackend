
package ca.sunlife.poc.boogle.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.util.BoogleUtil;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@Value("${BAD_REQUEST_MSG}")
	String msg;

	@ExceptionHandler({ Exception.class, FatalException.class })
	public ResponseEntity<Object> defaultExceptionHandler(final Exception exp) {
		return new ResponseEntity<Object>(BoogleUtil.mapErrorResponse(exp.getMessage(),Constants.INTERNAL_SERVER_ERROR_CODE),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> meessageNotReadableExceptionHandler() {
		return new ResponseEntity<Object>(BoogleUtil.mapErrorResponse(msg,Constants.BAD_REQUEST_ERROR_CODE), HttpStatus.BAD_REQUEST);

	}
}
