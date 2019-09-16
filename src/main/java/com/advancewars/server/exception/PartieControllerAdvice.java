package com.advancewars.server.exception;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PartieControllerAdvice {
		
	@ResponseBody
	@ExceptionHandler(PartieNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors PartieNotFoundExceptionHandler(PartieNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
	
	@ResponseBody
	@ExceptionHandler(AlreadyInGame.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors AlreadyInGame(AlreadyInGame ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
}
