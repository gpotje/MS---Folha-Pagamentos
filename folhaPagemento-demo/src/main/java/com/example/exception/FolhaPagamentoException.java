package com.example.exception;


public class FolhaPagamentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FolhaPagamentoException(String errorMessage) {
		super(errorMessage);
	}

}
