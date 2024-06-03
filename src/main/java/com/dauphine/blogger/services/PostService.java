package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Interface defining operations for managing posts in a blog.
 * This interface defines methods for retrieving, creating, updating, and deleting posts, as well as retrieving posts by category.
 * </p>
 *
 * <p>
 * All methods in this interface may throw {@link PostNotFoundByIdException} if the specified post ID does not exist.
 * For methods that involve category IDs, they may also throw {@link CategoryNotFoundByIdException} if the specified category ID does not exist.
 * </p>
 *
 * <p>
 * This interface is designed to provide a contract for classes implementing post management functionality.
 * The methods defined here offer operations to interact with post entities in the underlying data source.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface PostService {

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post
     * @return The post with the specified ID
     * @throws PostNotFoundByIdException if the post with the specified ID does not exist
     */
    Post getPost(UUID id) throws PostNotFoundByIdException;

    /**
     * Retrieves all posts.
     *
     * @return A list of all posts
     */
    List<Post> getPosts();

    /**
     * Retrieves posts by topic.
     *
     * @param topic The topic of the posts to retrieve
     * @return A list of posts with the specified topic
     */
    List<Post> getPostsByTopic(String topic);

    /**
     * Retrieves posts belonging to a specific category.
     *
     * @param categoryId The ID of the category
     * @return A list of posts belonging to the specified category
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     */
    List<Post> getPostsByCategoryId(UUID categoryId) throws CategoryNotFoundByIdException;

    /**
     * Creates a new post with the specified title, content, and category ID.
     *
     * @param title      The title of the post
     * @param content    The content of the post
     * @param categoryId The ID of the category
     * @return The newly created post
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     */
    Post createPost(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException;

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
    Post update(UUID id, String title, String content, UUID categoryId) throws CategoryNotFoundByIdException, PostNotFoundByIdException;

    /**
     * Deletes a post with the specified ID.
     *
     * @param id The ID of the post to delete
     * @return true if the post was deleted successfully, false otherwise
     * @throws PostNotFoundByIdException if the specified post ID does not exist
     */
    boolean deletePost(UUID id) throws PostNotFoundByIdException;

}
