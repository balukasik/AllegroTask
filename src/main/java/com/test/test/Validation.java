package com.test.test;

import org.springframework.web.client.HttpClientErrorException;

public class Validation {
	
	public static final String urlTemplate = "https://api.github.com/users/%s/repos?per_page=100&page=%d";
	public static final String urlGithubApiLimit = "https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting";

	public static void validateUser(String user) {
		if (user.length() == 0) {
			throw new UserNotSpecifiedException(user);
		} else if (user.length() > 40) {
			throw new UserNotFoundException(user);
		}
	}

	public static void githubError(HttpClientErrorException e, String user) {

		if (e.getResponseBodyAsString()
				.contains(urlGithubApiLimit)) {
			throw new LimitExceededException("Too many request, check "+ urlGithubApiLimit);
		}
		throw new UserNotFoundException(user);
	}
}
