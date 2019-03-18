package com.in28minutes.rest.webservices.user;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private LocalDateTime instant;
	private int error;
	private String errorDescription;
	private String message;
	
	private ExceptionResponse() {}

	private ExceptionResponse(LocalDateTime instant, int error,
			String errorDescription, String message) {
		super();
		this.instant = instant;
		this.error = error;
		this.errorDescription = errorDescription;
		this.message = message;
	}
	
	
	public static class ExceptionResponseBuilder {
		private LocalDateTime instant;
		private int error;
		private String errorDescription;
		private String message;
		
		public static ExceptionResponseBuilder with() {
			return new ExceptionResponseBuilder();
		}
		
		public ExceptionResponseBuilder instantNow() {
			this.instant = LocalDateTime.now();
			return this;
		}
		
		public ExceptionResponseBuilder withInstant(final LocalDateTime instant) {
			this.instant = instant;
			return this;
		}
		
		public ExceptionResponseBuilder withError(final HttpStatus httpStatus) {
			this.error = httpStatus.value();
			this.errorDescription = httpStatus.getReasonPhrase();
			return this;
		}
		
		public ExceptionResponseBuilder withMessage(final String message) {
			this.message = message;
			return this;
		}
		
		public ExceptionResponse build() {
			return new ExceptionResponse(this.instant, this.error, this.errorDescription, this.message);
		}
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public int getError() {
		return error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public String getMessage() {
		return message;
	}
}