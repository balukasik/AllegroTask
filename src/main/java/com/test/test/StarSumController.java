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
		Validation.validateUser(user);
		try {
			return getRepoStars(user);
		} catch (HttpClientErrorException e) {
			throw new GithubException(e, user);
		}
	}

	private Stars getRepoStars(String user) throws HttpClientErrorException {
		RestTemplate restTemplate = new RestTemplate();
		long summarize = 0;
		int i = 1;
		Stars[] repos;
		do {
			repos = restTemplate.getForObject(String.format(Validation.urlTemplate, user, i), Stars[].class);
			for (Stars s : repos) {
				summarize += s.getStargazers_count();
			}
			i++;
		} while (repos.length != 0);
		return new Stars(summarize);
	}
}
