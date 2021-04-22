package com.test.test;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoListController {
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/repoList")
	public RepoList repoList(@RequestParam(value = "user", defaultValue = "-") String name) {
		return new RepoList(counter.incrementAndGet(), name);
	}

	@GetMapping("/starsSum")
	public Stars starsSum(@RequestParam(value = "user", defaultValue = "-") String name) {
		return new Stars(counter.incrementAndGet(), name);
	}

	
}