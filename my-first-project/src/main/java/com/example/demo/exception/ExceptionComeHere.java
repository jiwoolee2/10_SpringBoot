package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionComeHere {
	
	@ExceptionHandler
	public ResponseEntity<?> ExistIdError(ExistIdException e){
		
		Map<String, String> error = new HashMap();
		error.put("error-message", e.getMessage());
		
		// responseEntity뒤에 위치하는거에 따라 상태코드가 달라지는거임
		// badRequest는 상태코드 400
		return ResponseEntity.badRequest().body(error);
	}

}
