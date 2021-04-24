package com.test.test;

public class Stars {
	private final long stargazers_count;

public Stars() {
	this.stargazers_count = 0;
}
public Stars(long stars) {
	this.stargazers_count = stars;
}

public long getStargazers_count() {
	return stargazers_count;
}
}
