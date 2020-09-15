package com.tribehired.com.posts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Post implements Comparable<Post>{
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int userId;
    private int id;
    private String title;
    private String body;
    private int numberOfComments = 0;
	@Override
	public int compareTo(Post o) {
		return o.numberOfComments - this.numberOfComments;
	}

}
