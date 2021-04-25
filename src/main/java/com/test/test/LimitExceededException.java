package com.test.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class LimitExceededException extends RuntimeException {

	public LimitExceededException(String content) {
		super(content);
	}
}

