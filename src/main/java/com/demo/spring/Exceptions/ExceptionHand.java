package com.demo.spring.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.demo.spring.Model.ApiError;

@ControllerAdvice

public class ExceptionHand extends ResponseEntityExceptionHandler{

	
	private ResponseEntity<Object> buildResponseEntity1(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	 }
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
         HttpHeaders headers,
         HttpStatus status,
         WebRequest request)
	{
		String message="No handler found for this request";
		return buildResponseEntity1(new ApiError(HttpStatus.BAD_REQUEST, message, ex));
	}
	
	 @Override
	   protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request)
	   {
		   String error="Please specify correct parameters";
		   return buildResponseEntity1(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
		   
	   }
	 
	 
	 @ExceptionHandler(NoSuchElementException.class)
	 protected ResponseEntity<Object> handleNullPointerException(NoSuchElementException ex)
	 {
		 String error="No entreis in the database for this entity";
		   return buildResponseEntity1(new ApiError(HttpStatus.NOT_FOUND, error, ex));
	 }
}
