package com.in28minutes.rest.webservices.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.user.ExceptionResponse.ExceptionResponseBuilder;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {

		final String errorMessage = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();

		final ExceptionResponse exceptionResponse = ExceptionResponseBuilder.with().instantNow().withError(HttpStatus.INTERNAL_SERVER_ERROR)
				.withMessage(errorMessage).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest webRequest) {

		final ExceptionResponse exceptionResponse = ExceptionResponseBuilder.with().instantNow().withError(HttpStatus.NOT_FOUND)
				.withMessage(ex.getMessage()).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		final StringBuilder messages = new StringBuilder();

		ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).forEach(e -> messages.append(e).append(" "));
		
		final ExceptionResponse exceptionResponse = ExceptionResponseBuilder.with().instantNow().withError(status)
				.withMessage(StringUtils.removeEnd(messages.toString(), " ")).build();

		return new ResponseEntity<>(exceptionResponse, headers, status);
	}
}