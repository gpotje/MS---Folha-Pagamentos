package com.example.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class GlobalControllerAdvice {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseDefault> handleNoContent(BusinessException businessException){
		ResponseDefault response = new ResponseDefault(businessException.getErrorCode(),businessException.getErrorMessage());
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		if(response.getCode().equals("01"))httpStatus = HttpStatus.NOT_FOUND;
		if(response.getCode().equals("10"))httpStatus = HttpStatus.BAD_REQUEST;
		 
		return new ResponseEntity<>(response,httpStatus);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ResponseDefault> handleConstraintViolationException(ConstraintViolationException e){
		return new ResponseEntity<>( new ResponseDefault("Entity does not contain valid values",e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new ResponseDefault("101", "Integrity error occurred"), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ExpiredJwtExceptio.class)
//    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception) {
//        return new ResponseEntity<>(new ResponseDefault("101", "O token expirou. obtenha um novo token e tente novamente."), HttpStatus.BAD_REQUEST);
//    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return new ResponseEntity<>(new ResponseDefault("100", "An unknown error has occurred"), HttpStatus.BAD_REQUEST);
    }  
	
	
}
