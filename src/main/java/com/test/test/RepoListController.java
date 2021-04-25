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
	public List<Repo> repoList(@RequestParam(value = "user", defaultValue = "") String user){
		validateUser(user);
		try {
			return getRepoList(user);
		} catch (HttpClientErrorException e) {
			Validation.githubError(e, user);
			return new ArrayList<Repo>();
		}	
	}

	private void validateUser(String user) {
		if (user.length() == 0) {
			throw new UserNotSpecifiedException(user);
		}else if (user.length()>40) {
			throw new UserNotFoundException(user);
		}		
	}

	private List<Repo> getRepoList(String user) throws HttpClientErrorException {

		RestTemplate restTemplate = new RestTemplate();
		List<Repo> list = new ArrayList<Repo>();
		Repo[] repos;
		int i = 1;
		do {
			repos = restTemplate.getForObject(String.format(Validation.urlTemplate, user, i), Repo[].class);
			list.addAll(Arrays.asList(repos));
			i++;
		} while (repos.length != 0);
		return list;
	}

}