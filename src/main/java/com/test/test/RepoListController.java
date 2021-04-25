package com.test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class RepoListController {
	// private final AtomicLong counter = new AtomicLong();

	@GetMapping("/repoList")
	public List<Repo> repoList(@RequestParam(value = "user", defaultValue = "") String user) {
		if (user.length() == 0) {
			return null;
		}
		try {
			return getRepoList(user);
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@GetMapping("/starsSum")
	public Stars starsSum(@RequestParam(value = "user", defaultValue = "") String user) {
		if (user.length() == 0) {
			return null;
		}
		try {
			return getRepoStars(user);
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	private Stars getRepoStars(String user) throws HttpClientErrorException {
		final String urlTemplate = "https://api.github.com/users/%s/repos?page=%d";
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

	private List<Repo> getRepoList(String user) throws HttpClientErrorException {

		final String urlTemplate = "https://api.github.com/users/%s/repos?page=%d";
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