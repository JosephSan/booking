package com.smarthost.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The datafile provided as test data is badly formatted")
public class BadFormattedDataFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
