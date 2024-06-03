package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Implementation of the PostService interface.
 * Provides methods to manage blog posts, including retrieving, creating, updating, and deleting posts.
 * </p>
 *
 * <p>
 * All methods in this class may throw {@link PostNotFoundByIdException} if the specified post ID does not exist.
 * For methods that involve creating or updating posts, they may also throw {@link CategoryNotFoundByIdException} if the specified category ID does not exist.
 * </p>
 *
 * <p>
 * This class is responsible for implementing the business logic associated with post management.
 * It interacts with the underlying data source through PostRepository and CategoryRepository.
 * </p>
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
     * @throws PostNotFoundByIdException if the post with the specified ID does not exist
     */
    public Post getPost(UUID id) throws PostNotFoundByIdException {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
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
     * Retrieves posts by topic.
     *
     * @param topic The topic of the posts to retrieve
     * @return A list of posts with the specified topic
     */
    @Override
    public List<Post> getPostsByTopic(String topic) {
        return postRepository.findByTitleOrContentContainingKeyword(topic);
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
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     */
    @Override
    public Post createPost(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));

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
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     * @throws PostNotFoundByIdException     if the specified post ID does not exist
     */
    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) throws CategoryNotFoundByIdException, PostNotFoundByIdException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));

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
     * @throws PostNotFoundByIdException if the specified post ID does not exist
     */
    @Override
    public boolean deletePost(UUID id) throws PostNotFoundByIdException {
        boolean exists = postRepository.existsById(id);

        if (!exists) {
            throw new PostNotFoundByIdException(id);
        }

        postRepository.deleteById(id);

        return true;
    }

}
