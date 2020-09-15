package com.tribehired.com.posts.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostPagination {

	private int pageNumber;
	private int total;
	private Object[] posts;
	
}
