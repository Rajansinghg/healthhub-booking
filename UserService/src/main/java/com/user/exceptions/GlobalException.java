package com.user.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.payload.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex,WebRequest req){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),req.getDescription(false),LocalDateTime.now());
		return ResponseEntity.ok(exceptionResponse);
	}
	
}
