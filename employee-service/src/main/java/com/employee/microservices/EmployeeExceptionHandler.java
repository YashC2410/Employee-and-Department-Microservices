package com.employee.microservices;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllEmployeeExceptions(Exception e, WebRequest request){
		EmployeeExceptionResponse exceptionResponse = new EmployeeExceptionResponse(400,e.getMessage(),
				new Date(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse , HttpStatus.BAD_REQUEST);
	}
}
