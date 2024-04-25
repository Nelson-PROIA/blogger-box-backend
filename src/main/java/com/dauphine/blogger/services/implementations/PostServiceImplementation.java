package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the PostService interface.
 * Provides methods to manage blog posts, including retrieving, creating, updating, and deleting posts.
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@Service
public class PostServiceImplementation implements PostService {

    /**
     * Repository for managing post entities.
     */
    private final PostRepository postRepository;

    /**
     * Repository for managing category entities.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a new PostServiceImplementation object with the specified repositories.
     *
     * @param postRepository     Repository for managing post entities
     * @param categoryRepository Repository for managing category entities
     */
    public PostServiceImplementation(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post to retrieve
     * @return The post with the specified ID
     * @throws RuntimeException if the post with the specified ID does not exist
     */
    public Post getPost(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id " + id + " does not exist!"));
    }

    /**
     * Retrieves all posts.
     *
     * @return A list of all posts
     */
    @Override
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedDate();
    }

    /**
     * Retrieves posts belonging to a specific category.
     *
     * @param categoryId The ID of the category
     * @return A list of posts belonging to the specified category
     */
    @Override
    public List<Post> getPostsByCategoryId(UUID categoryId) {
        return postRepository.findAllByCategoryId(categoryId);
    }

    /**
     * Creates a new post with the specified title, content, and category ID.
     *
     * @param title      The title of the post
     * @param content    The content of the post
     * @param categoryId The ID of the category
     * @return The newly created post
     * @throws RuntimeException if the specified category ID does not exist
     */
    @Override
    public Post createPost(String title, String content, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " does not exist!"));

        Post post = new Post(title, content, category);

        return postRepository.save(post);
    }

    /**
     * Updates an existing post with the specified ID, title, content, and category ID.
     *
     * @param id         The ID of the post to update
     * @param title      The new title for the post
     * @param content    The new content for the post
     * @param categoryId The ID of the category
     * @return The updated post
     * @throws RuntimeException if the specified post ID or category ID does not exist
     */
    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " does not exist!"));

        Post post = getPost(id);

        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);

        return postRepository.save(post);
    }

    /**
     * Deletes a post with the specified ID.
     *
     * @param id The ID of the post to delete
     * @return true if the post was deleted successfully, false otherwise
     */
    @Override
    public boolean deletePost(UUID id) {
        boolean deleted = postRepository.existsById(id);

        postRepository.deleteById(id);

        return deleted;
    }

}
