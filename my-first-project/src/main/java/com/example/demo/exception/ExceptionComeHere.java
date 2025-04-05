package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionComeHere {
	
	@ExceptionHandler
	public ResponseEntity<?> ExistIdError(ExistIdException e){
		
		String error = e.getMessage();
		
		// responseEntity뒤에 위치하는거에 따라 상태코드가 달라지는거임
		// badRequest는 상태코드 400
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> WrongIdPasswordError(WrongIdPasswordException e){
		String error = e.getMessage();
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> DuplicatedLikeError(DuplicatedLikeException e){
		String error = e.getMessage();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
	

}
