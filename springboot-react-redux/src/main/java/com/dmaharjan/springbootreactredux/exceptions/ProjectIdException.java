package com.dmaharjan.springbootreactredux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ProjectIdException extends RuntimeException {

	public ProjectIdException(String message) {
		super(message);
	}

}
