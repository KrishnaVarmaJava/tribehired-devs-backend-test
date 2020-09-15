package com.tribehired.com.posts.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tribehired.com.posts.model.Comment;
import com.tribehired.com.posts.model.Post;
import com.tribehired.com.posts.model.PostPagination;

@Service
public class PostsService {

	private final static String BASE_URL = "https://jsonplaceholder.typicode.com"; 
	private final RestTemplate restTemplete;
	private static final int PAGE_SIZE = 3;
	public Post[] POSTS = {};
	public Comment[] COMMENTS = {};

	public PostsService(RestTemplateBuilder templateBuilder) {
		this.restTemplete = templateBuilder.build();
	}

	@PostConstruct
	private void init() {
		if(POSTS.length==0) {
			System.out.println("inside if");
			POSTS = this.restTemplete.getForObject(BASE_URL+"/posts", Post[].class);
		}
		if(COMMENTS.length==0) {
			System.out.println("inside if");
			COMMENTS = this.restTemplete.getForObject(BASE_URL+"/comments", Comment[].class);
		}
	}

	public PostPagination getPostsAsObject(Integer page) {
		Arrays.stream(POSTS).forEach(post -> {
			Arrays.stream(COMMENTS).forEach(comment -> {
				if(post.getId() == comment.getPostId()) {
					int count = post.getNumberOfComments();
					count ++;
					post.setNumberOfComments(count);
				}
			});
		});
		Arrays.sort(POSTS);
		return postPagination(page, POSTS);
	}

	public PostPagination searchComment(Integer pageNumber, String search, Integer postId) {
		Comment[] comments = {};
		if(search != null) {
			comments = searchByEmail(search);
		}else if(postId != null) {
			comments = searchByPostId(postId);
		}
		else {
			comments = COMMENTS;
		}
		return postPagination(pageNumber, comments);

	}

	private Comment[] searchByPostId(Integer postId) {
		List<Comment> newComments = Arrays.stream(COMMENTS)
				.filter(comment -> comment.getPostId() == postId)
				.collect(Collectors.toList());
		Comment[] comments = new Comment[newComments.size()];
		return newComments.toArray(comments);
	}

	private Comment[] searchByEmail(String search) {
		List<Comment> newComments = Arrays.stream(COMMENTS)
				.filter(comment -> search.equals(comment.getEmail()))
				.collect(Collectors.toList());
		Comment[] comments = new Comment[newComments.size()];
		return newComments.toArray(comments);
	}

	private PostPagination postPagination(Integer pageNumber, Object[] posts) {
		if(!isValidPageNumber(pageNumber, posts)) {
			return null;
		}
		int start = (pageNumber - 1)*PAGE_SIZE;
		int end = start + PAGE_SIZE > posts.length ? posts.length : start + PAGE_SIZE;
		PostPagination pagination = new PostPagination();
		pagination.setPageNumber(pageNumber);
		pagination.setPosts(Arrays.copyOfRange(posts, start, end));
		pagination.setTotal(posts.length);
		return pagination;		
	}
	private boolean isValidPageNumber(Integer pageNumber, Object[] posts) {
		int start = (pageNumber - 1)*PAGE_SIZE;
		return start < posts.length;
	}

}
