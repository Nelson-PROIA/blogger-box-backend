package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.implementations.PostServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>
 * Unit tests for the PostServiceImplementation class.
 * These tests validate the functionality of the service methods and ensure correct interaction with the PostRepository and CategoryRepository.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class PostServiceImplementationTest {

    /**
     * Mocked PostRepository used to simulate the repository layer for post operations.
     */
    @Mock
    private PostRepository postRepository;

    /**
     * Mocked CategoryRepository used to simulate the repository layer for category operations.
     */
    @Mock
    private CategoryRepository categoryRepository;

    /**
     * The service implementation being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private PostServiceImplementation postService;

    /**
     * Setup method to initialize mocks and the service implementation before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to verify that getPost returns a post when the post exists.
     *
     * @throws PostNotFoundByIdException if the post with the specified ID does not exist
     */
    @Test
    public void getPost_ReturnsPost_WhenPostExists() throws PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();
        Post post = new Post("Title", "Content", new Category("Category"));

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Post foundPost = postService.getPost(postId);

        assertNotNull(foundPost);
        assertEquals(post, foundPost);
    }

    /**
     * Test to verify that getPost throws an exception when the post does not exist.
     */
    @Test
    public void getPost_ThrowsException_WhenPostDoesNotExist() {
        UUID postId = UUID.randomUUID();

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundByIdException.class, () -> postService.getPost(postId));
    }

    /**
     * Test to verify that getPosts returns a list of all posts.
     */
    @Test
    public void getPosts_ReturnsAllPosts() {
        List<Post> posts = List.of(new Post("Title1", "Content1", new Category("Category1")),
                new Post("Title2", "Content2", new Category("Category2")));

        when(postRepository.findAllByOrderByCreatedDate()).thenReturn(posts);

        List<Post> retrievedPosts = postService.getPosts();

        assertEquals(posts, retrievedPosts);
    }

    /**
     * Test to verify that getPostsByTopic returns posts matching the specified topic.
     */
    @Test
    public void getPostsByTopic_ReturnsPosts_WhenTopicExists() {
        String topic = "Topic";
        List<Post> posts = List.of(new Post("Title1", "Content with Topic", new Category("Category")));

        when(postRepository.findByTitleOrContentContainingKeyword(topic)).thenReturn(posts);

        List<Post> retrievedPosts = postService.getPostsByTopic(topic);

        assertEquals(posts, retrievedPosts);
    }

    /**
     * Test to verify that getPostsByCategoryId returns posts belonging to the specified category.
     */
    @Test
    public void getPostsByCategoryId_ReturnsPosts_WhenCategoryIdExists() {
        UUID categoryId = UUID.randomUUID();
        List<Post> posts = List.of(new Post("Title1", "Content1", new Category("Category")));

        when(postRepository.findAllByCategoryId(categoryId)).thenReturn(posts);

        List<Post> retrievedPosts = postService.getPostsByCategoryId(categoryId);

        assertEquals(posts, retrievedPosts);
    }

    /**
     * Test to verify that createPost creates a new post when the category exists.
     *
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     */
    @Test
    public void createPost_CreatesPost_WhenCategoryExists() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();
        Category category = new Category("Category");
        Post post = new Post("Title", "Content", category);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post createdPost = postService.createPost("Title", "Content", categoryId);

        assertNotNull(createdPost);
        assertEquals(post, createdPost);
    }

    /**
     * Test to verify that createPost throws an exception when the category does not exist.
     */
    @Test
    public void createPost_ThrowsException_WhenCategoryDoesNotExist() {
        UUID categoryId = UUID.randomUUID();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundByIdException.class, () -> postService.createPost("Title", "Content", categoryId));
    }

    /**
     * Test to verify that update updates a post when the post and category exist.
     *
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     * @throws PostNotFoundByIdException     if the specified post ID does not exist
     */
    @Test
    public void update_UpdatesPost_WhenPostAndCategoryExist() throws CategoryNotFoundByIdException, PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();
        Category category = new Category("Category");
        Post post = new Post("Title", "Content", category);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post updatedPost = postService.update(postId, "New Title", "New Content", categoryId);

        assertEquals("New Title", updatedPost.getTitle());
        assertEquals("New Content", updatedPost.getContent());
        assertEquals(category, updatedPost.getCategory());
    }

    /**
     * Test to verify that update throws an exception when the post does not exist.
     */
    @Test
    public void update_ThrowsException_WhenPostDoesNotExist() {
        UUID postId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();
        Category category = new Category("Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundByIdException.class, () -> postService.update(postId, "New Title", "New Content", categoryId));
    }

    /**
     * Test to verify that update throws an exception when the category does not exist.
     */
    @Test
    public void update_ThrowsException_WhenCategoryDoesNotExist() {
        UUID postId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundByIdException.class, () -> postService.update(postId, "New Title", "New Content", categoryId));
    }

    /**
     * Test to verify that deletePost deletes a post when the post exists.
     *
     * @throws PostNotFoundByIdException if the specified post ID does not exist
     */
    @Test
    public void deletePost_DeletesPost_WhenPostExists() throws PostNotFoundByIdException {
        UUID postId = UUID.randomUUID();

        when(postRepository.existsById(postId)).thenReturn(true);
        doNothing().when(postRepository).deleteById(postId);

        boolean deleted = postService.deletePost(postId);

        assertTrue(deleted);
        verify(postRepository, times(1)).deleteById(postId);
    }

    /**
     * Test to verify that deletePost throws an exception when the post does not exist.
     */
    @Test
    public void deletePost_ThrowsException_WhenPostDoesNotExist() {
        UUID postId = UUID.randomUUID();

        when(postRepository.existsById(postId)).thenReturn(false);

        assertThrows(PostNotFoundByIdException.class, () -> postService.deletePost(postId));
    }

}
