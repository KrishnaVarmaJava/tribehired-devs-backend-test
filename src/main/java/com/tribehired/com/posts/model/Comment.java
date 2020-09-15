package com.tribehired.com.posts.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Comment implements Serializable{

	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
}
