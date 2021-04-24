package com.test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RepoListController {
	// private final AtomicLong counter = new AtomicLong();

	@GetMapping("/repoList")
	public Repo[] repoList(@RequestParam(value = "user", defaultValue = "-") String user) {
		return getRepoList(user);
	}

	@GetMapping("/starsSum")
	public Stars starsSum(@RequestParam(value = "user", defaultValue = "-") String user) {
		return getRepoStars(user);
	}

	private Stars getRepoStars(String user) {
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
	
	private Repo[] getRepoList(String user) {

		final String urlTemplate = "https://api.github.com/users/%s/repos?page=%d";
		RestTemplate restTemplate = new RestTemplate();
		List list = new ArrayList();
		Repo[] repos;
		int i =1;
		do {
			repos = restTemplate.getForObject(String.format(urlTemplate, user, i), Repo[].class);
			list.addAll(Arrays.asList(repos));     
			i++;
		} while (repos.length != 0);
		Repo[] finalArray = (Repo[]) list.toArray();     
		return finalArray;

	}

}