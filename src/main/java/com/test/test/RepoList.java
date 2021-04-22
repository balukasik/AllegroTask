package com.test.test;

import org.springframework.web.client.RestTemplate;

public class RepoList {

		private final long id;
		private final String content;
		
		public RepoList(long id, String user) {
			super();
			this.id = id;
			this.content = getRepoList(user);
		}

		public long getId() {
			return id;
		}
		
		public String getContent() {
			return content;
		}
		
		private String getRepoList(String user) {

			final String urlTemplate = "https://api.github.com/users/%s/repos";
			RestTemplate restTemplate = new RestTemplate();

			return restTemplate.getForObject(String.format(urlTemplate, user), String.class);
		}
}
