package com.tribehired.com.posts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tribehired.com.posts.model.PostPagination;
import com.tribehired.com.posts.service.PostsService;

@RestController
@RequestMapping("/api/v1")
public class MainController {
	
	@Autowired
	PostsService postService;

	@GetMapping("/getposts")
	public PostPagination getPosts(
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer page) {
		return postService.getPostsAsObject(page);
	}
	
	@GetMapping("/search")
	public PostPagination getSearch(
			@RequestParam(name = "postId", required = false) Integer id,
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "search", required = false) String searchEmail) {
		return postService.searchComment(page, searchEmail, id);
	}
}
