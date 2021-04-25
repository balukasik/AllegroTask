package com.test.test;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RControllerdAdvice {
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException ex) {
		return "{" + '"' + "message" + '"' + ": " + '"' + ex.getMessage() + '"' + "}";
	}

	@ResponseBody
	@ExceptionHandler(UserNotSpecifiedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotSpecified(UserNotSpecifiedException ex) {
		return "{" + '"' + "message" + '"' + ": " + '"' + ex.getMessage() + '"' + "}";
	}
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handleBadRequests(HttpServletResponse response){
	    return "{" + '"' + "message" + '"' + ": " + '"' + "wrong request" + '"' + "}";
	}
	@ResponseBody
	@ExceptionHandler(LimitExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String limitExceededException(LimitExceededException ex){
		return "{" + '"' + "message" + '"' + ": " + '"' + ex.getMessage() + '"' + "}";
	}

}
