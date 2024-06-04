package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * <p>
 * Unit tests for the PostController class.
 * These tests validate the functionality of PostController's endpoints for managing posts.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class PostControllerTest {

    /**
     * Mocked PostService used to simulate the service layer for post operations.
     */
    @Mock
    private PostService postService;

    /**
     * The controller being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private PostController postController;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that getPosts returns all posts.
     */
    @Test
    public void getPosts_ReturnsAllPosts() {
        List<Post> posts = List.of(new Post("Title1", "Content1", null), new Post("Title2", "Content2", null));

        when(postService.getPosts()).thenReturn(posts);

        ResponseEntity<List<Post>> response = postController.getPosts(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    /**
     * Tests that getPosts returns filtered posts.
     */
    @Test
    public void getPosts_ReturnsFilteredPosts() {
        String topic = "Technology";
        List<Post> posts = List.of(new Post("Tech Post", "Tech Content", null));

        when(postService.getPostsByTopic(topic)).thenReturn(posts);

        ResponseEntity<List<Post>> response = postController.getPosts(topic);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    /**
     * Tests that postPost creates a post when the category exists.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void postPost_CreatesPost_WhenCategoryExists() throws CategoryNotFoundByIdException {
        String title = "New Post";
        String content = "Content of the new post";
        UUID categoryId = UUID.randomUUID();
        PostRequest postRequest = new PostRequest(title, content, categoryId);
        Post post = new Post(title, content, null);
        post.setId(UUID.randomUUID());

        when(postService.createPost(title, content, categoryId)).thenReturn(post);

        ResponseEntity<Post> response = postController.postPost(postRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(post, response.getBody());
        assertEquals(URI.create("/v1/posts/" + post.getId()), response.getHeaders().getLocation());
    }

    /**
     * Tests that postPost throws an exception when the category does not exist.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void postPost_ThrowsException_WhenCategoryDoesNotExist() throws CategoryNotFoundByIdException {
        String title = "New Post";
        String content = "Content of the new post";
        UUID categoryId = UUID.randomUUID();
        PostRequest postRequest = new PostRequest(title, content, categoryId);

        when(postService.createPost(title, content, categoryId)).thenThrow(new CategoryNotFoundByIdException(categoryId));

        assertThrows(CategoryNotFoundByIdException.class, () -> postController.postPost(postRequest));
    }

    /**
     * Tests that putPost updates the post when it exists along with the category.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     * @throws PostNotFoundByIdException     if the post is not found
     */
    @Test
    public void putPost_UpdatesPost_WhenPostAndCategoryExist() throws CategoryNotFoundByIdException, PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();
        String title = "Updated Post";
        String content = "Updated content";
        UUID categoryId = UUID.randomUUID();
        PostRequest postRequest = new PostRequest(title, content, categoryId);
        Post updatedPost = new Post(title, content, null);
        updatedPost.setId(postId);

        when(postService.update(postId, title, content, categoryId)).thenReturn(updatedPost);

        ResponseEntity<Post> response = postController.putPost(postId, postRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPost, response.getBody());
    }

    /**
     * Tests that putPost throws an exception when the post does not exist.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     * @throws PostNotFoundByIdException     if the post is not found
     */
    @Test
    public void putPost_ThrowsException_WhenPostDoesNotExist() throws CategoryNotFoundByIdException, PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();
        String title = "Updated Post";
        String content = "Updated content";
        UUID categoryId = UUID.randomUUID();
        PostRequest postRequest = new PostRequest(title, content, categoryId);

        when(postService.update(postId, title, content, categoryId)).thenThrow(new PostNotFoundByIdException(postId));

        assertThrows(PostNotFoundByIdException.class, () -> postController.putPost(postId, postRequest));
    }

    /**
     * Tests that deletePost deletes a post when it exists.
     *
     * @throws PostNotFoundByIdException if the post is not found
     */
    @Test
    public void deletePost_DeletesPost_WhenPostExists() throws PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();

        when(postService.deletePost(postId)).thenReturn(true);

        ResponseEntity<Boolean> response = postController.deletePost(postId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    /**
     * Tests that deletePost throws an exception when the post does not exist.
     *
     * @throws PostNotFoundByIdException if the post is not found
     */
    @Test
    public void deletePost_ThrowsException_WhenPostDoesNotExist() throws PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();

        when(postService.deletePost(postId)).thenThrow(new PostNotFoundByIdException(postId));

        assertThrows(PostNotFoundByIdException.class, () -> postController.deletePost(postId));
    }

}
