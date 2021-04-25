package com.test.test;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class MyErrorController implements ErrorController {

	private final String errorPath = "/error";

	@RequestMapping(value = errorPath)
	String handleError(WebRequest request) {
		return "{" + '"' + "message" + '"' + ": " + '"' + "wrong request" + '"' + "}";
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}
}
