package com.kh.start.exception;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<?> makeResponseEntity(RuntimeException e , HttpStatus status){
		Map<String, String> error = new HashMap();
		error.put("error-message", e.getMessage());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(InvalidUserRequestException.class)
	public ResponseEntity<?> handleInvalidUserError(InvalidUserRequestException e){
		
		return makeResponseEntity(e,HttpStatus.UNAUTHORIZED);
	}
	
	
	
	// service에서 발생시킨 예외처리 == 아이디 중복이었던가
	@ExceptionHandler(MemberIdDuplicateException.class)
	public ResponseEntity<?> handleDuplicateMemberId(MemberIdDuplicateException e){
		
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationError(CustomAuthenticationException e){
		
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	
	// DTO에서 작성한 예외를 처리 == 아이디,비빌번호 입력값 조건들 안맞는경우
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentsNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap();

		 // List list = e.getBindingResult().getFieldErrors();
		/*
		 * for(int i=0 ; i<list.size(); i++) { log.info("예외가 발생한 필드명 : {}, 이유 : {}",
		 * ((FieldError)list.get(i)).getField(),
		 * ((FieldError)list.get(i)).getDefaultMessage()); }
		 */
		e.getBindingResult()
		 .getFieldErrors()
		 .forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
		
		
		return ResponseEntity.badRequest().body(errors);
	}
	
}
