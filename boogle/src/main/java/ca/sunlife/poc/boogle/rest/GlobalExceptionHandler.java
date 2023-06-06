package ca.sunlife.poc.boogle.rest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.response.ResponseDto;
import ca.sunlife.poc.boogle.util.BoogleUtil;
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler({ Exception.class, FatalException.class })
	public ResponseEntity<ResponseDto<Object>> generateGenericErrorMessage(final Exception exp) {
		ResponseDto<Object> response = BoogleUtil.mapResponse(Constants.FAILURE, Constants.GENERIC_FAILURE_ERROR_CODE, exp.getMessage(), null);
		return new ResponseEntity<ResponseDto<Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler({ BoogleException.class })
	public ResponseEntity<ResponseDto<Object>> generateGenericErrorMessage(final BoogleException exp) {
		ResponseDto<Object> response = BoogleUtil.mapResponse(Constants.SUCCESSFUL, exp.getErrorCode(), exp.getMessage(), null);
		return new ResponseEntity<ResponseDto<Object>>(response,HttpStatus.OK);

	}
}
