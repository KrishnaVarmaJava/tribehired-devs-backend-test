package com.tribehired.com.posts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tribehired.com.posts.service.PostsService;

@SpringBootTest
class PostServiceTest {
	
	@Autowired
	PostsService postsService;
	
	@BeforeAll
	public static void setup() {
	}

	@Test
	void testGetPostsAsObject() {
		assertEquals(postsService.getPostsAsObject(2).getPageNumber(), 2);
	}

	@Test
	void testSearchComment() {
		assertEquals(postsService.searchComment(2, null, null).getPageNumber(), 2);
	}

}
