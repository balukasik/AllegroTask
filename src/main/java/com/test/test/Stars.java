package com.test.test;

import org.springframework.web.client.RestTemplate;

public class Stars {
	
	private final long id;
	private final String content;
	
	public Stars(long id, String user) {
		super();
		this.id = id;
		this.content = getSum(user);
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	private String getSum(String user) {

		final String urlTemplate = "https://api.github.com/users/%s/repos";
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(String.format(urlTemplate, user), String.class);
	}
	
}