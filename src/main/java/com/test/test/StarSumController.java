package com.test.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class StarSumController {

	@RequestMapping("/starSum")
	public Stars starsSum(@RequestParam(value = "user", defaultValue = "") String user) throws UserNotFoundException {
		if (user.length() == 0) {
			throw new UserNotSpecifiedException(user);
		}
		try {
			return getRepoStars(user);
		} catch (HttpClientErrorException e) {
			if (e.getResponseBodyAsString()
					.contains("https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting")) {
				throw new LimitExceededException(
						"Too many request, check https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting");
			}
			throw new UserNotFoundException(user);
		}
	}

	private Stars getRepoStars(String user) throws HttpClientErrorException {
		final String urlTemplate = "https://api.github.com/users/%s/repos?per_page=100&page=%d";
		RestTemplate restTemplate = new RestTemplate();
		long summarize = 0;
		int i = 1;
		Stars[] repos;
		do {
			repos = restTemplate.getForObject(String.format(urlTemplate, user, i), Stars[].class);
			for (Stars s : repos) {
				summarize += s.getStargazers_count();
			}
			i++;
		} while (repos.length != 0);
		return new Stars(summarize);
	}
}
