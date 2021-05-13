package com.ecommerce.app.controller;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MvcExceptionHandler {

	@ExceptionHandler
	public String handleInvalidFieldException(InvalidFileNameException fileNameException){
		return fileNameException.getName();
	}

}
