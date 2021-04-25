package com.test.test;

import org.springframework.web.client.HttpClientErrorException;

public class GithubException extends RuntimeException {


	private final HttpClientErrorException e;
	private final String user;

	public GithubException(HttpClientErrorException e, String user) {
		this.e = e;
		this.user = user;
	}
	
	public HttpClientErrorException getE() {
		return e;
	}

	public String getUser() {
		return user;
	}

}
