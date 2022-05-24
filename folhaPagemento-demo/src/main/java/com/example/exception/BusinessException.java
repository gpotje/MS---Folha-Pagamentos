package com.example.exception;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMessage;
	
	private List<String> errosMessages;
	
	 public BusinessException(String code, String message) {
	        this.setErrorCode(code);
	        this.setErrorMessage(message);
	    }
	 
	 public BusinessException(List<String> erros) {
	       this.setErrosMessages(erros);
	    }

	 
}
