package com.products.handlers;


import com.products.dto.APIResponse;
import com.products.exceptions.ErrorDetails;
import com.products.exceptions.InvalidCredentialsException;
import com.products.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@InitBinder
	private void activateDirectFieldAccess(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {

		APIResponse apiResponse = APIResponse
				.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.errors(Collections.singletonList(new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false))))
				.build();

		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}




	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException exception, WebRequest request) {

		APIResponse apiResponse = APIResponse
				.builder()
				.status(HttpStatus.UNAUTHORIZED.value())
				.errors(Collections.singletonList(new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false))))
				.build();
		return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
		APIResponse apiResponse = APIResponse
				.builder()
				.status(HttpStatus.UNAUTHORIZED.value())
				.errors(Collections.singletonList(new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false))))
				.build();
		return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<>("not valid due to validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {

		APIResponse<?> serviceResponse = new APIResponse<>();
		List<ErrorDetails> errors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(error -> {
					ErrorDetails errorDTO = new ErrorDetails(new Date(),error.getField(), error.getDefaultMessage());
					errors.add(errorDTO);
				});
		serviceResponse.setStatus(400);
		serviceResponse.setErrors(errors);
		return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception exception, WebRequest request) {

		log.error(exception.getMessage(), exception);

		APIResponse apiResponse = APIResponse
				.builder()
				.status(500)
				.errors(Collections.singletonList(new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false))))
				.build();
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
