package com.test.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotSpecifiedException extends RuntimeException {

	public UserNotSpecifiedException(String user) {
		super("user not specified");
	}
}
