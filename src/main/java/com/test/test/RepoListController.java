package com.test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class RepoListController {

	@GetMapping("/repoList")
	public List<Repo> repoList(@RequestParam(value = "user", defaultValue = "") String user)
			throws UserNotFoundException {
		if (user.length() == 0) {
			throw new UserNotSpecifiedException(user);
		}
		try {
			return getRepoList(user);
		} catch (HttpClientErrorException e) {
			if(e.getResponseBodyAsString().contains("https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting")) {
				throw new LimitExceededException("Too many request, check https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting");
			}
			throw new UserNotFoundException(user);
		}
	}

	private List<Repo> getRepoList(String user) throws HttpClientErrorException {

		final String urlTemplate = "https://api.github.com/users/%s/repos?per_page=100&page=%d";
		RestTemplate restTemplate = new RestTemplate();
		List<Repo> list = new ArrayList<Repo>();
		Repo[] repos;
		int i = 1;
		do {
			repos = restTemplate.getForObject(String.format(urlTemplate, user, i), Repo[].class);
			list.addAll(Arrays.asList(repos));
			i++;
		} while (repos.length != 0);
		return list;
	}

}