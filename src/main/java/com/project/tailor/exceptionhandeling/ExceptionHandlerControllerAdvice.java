package com.project.tailor.exceptionhandeling;

import org.slf4j.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	private static Logger log = LoggerFactory.getLogger(Slf4j.class);
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFound(BadRequestException e){

		log.debug("the product associated with id entered not found : the id must be wrong");
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> badRequestHandler(Exception e){
		
		log.debug("the enterd id in the requst is not a number");

		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	//ResponseEntity<ResponseDTO>
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionResponse>  handleValidationExceptions(
			DataIntegrityViolationException	 e) {
		
		log.debug("data validation error: the data entered dosen't match the asked constraints");
		
		/*
			Map<String, String> errors = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			String message = errors.toString();
			message = message.replace("{","");
			message = message.replace("}","");
			
			return ResponseEntity
				.badRequest()
				.body(new ResponseDTO(message,
						"Validation errors",
						400));
		*/
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
		
	
 /*   
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handlebException(Exception e){

		ExceptionResponse error= new ExceptionResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
}
