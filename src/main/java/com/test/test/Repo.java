package com.test.test;

public class Repo {
	private final String name;
	private final long stargazers_count;

	public Repo() {
		this.stargazers_count = 0;
		this.name = "";
	}

	public Repo(long id, String full_name) {
		this.stargazers_count = id;
		this.name = full_name;
	}

	public long getStargazers_count() {
		return stargazers_count;
	}

	@Override
	public String toString() {
		return stargazers_count + " " + name;
	}

	public String getName() {
		return name;
	}

}
