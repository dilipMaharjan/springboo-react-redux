package com.dmaharjan.springbootreactredux.service.error;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorHandlingService {

	public ResponseEntity<?> mapError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			HashMap<String, String> errorMap = new HashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<HashMap<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
