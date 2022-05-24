package com.example.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessExceptionFiledError  extends Exception{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	private List<FieldError> filFieldErrors;
	
	public BusinessExceptionFiledError(final List<FieldError> fieldErrors, final HttpStatus httpStatus) {
		super(HttpStatus.BAD_REQUEST.getReasonPhrase());
		this.filFieldErrors = fieldErrors;
		this.httpStatus = httpStatus;
	}
}
