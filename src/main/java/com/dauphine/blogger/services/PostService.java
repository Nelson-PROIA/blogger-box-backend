package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

/**
 * Interface defining operations for managing posts in a blog.
 * <p>
 * This interface defines methods for retrieving, creating, updating, and deleting posts,
 * as well as retrieving posts by category.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.eu
 */
public interface PostService {

    /**
     * Retrieves all posts.
     *
     * @return A list of all posts
     */
    List<Post> getPosts();

    /**
     * Retrieves posts belonging to a specific category.
     *
     * @param categoryId The ID of the category
     * @return A list of posts belonging to the specified category
     */
    List<Post> getPostsByCategoryId(UUID categoryId);

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post
     * @return The post with the specified ID, or null if not found
     */
    Post getPost(UUID id);

    /**
     * Creates a new post with the specified title, content, and category ID.
     *
     * @param title      The title of the post
     * @param content    The content of the post
     * @param categoryId The ID of the category
     * @return The newly created post
     */
    Post createPost(String title, String content, UUID categoryId);

    /**
     * Updates an existing post with the specified ID, title, content, and category ID.
     *
     * @param id         The ID of the post to update
     * @param title      The new title for the post
     * @param content    The new content for the post
     * @param categoryId The ID of the category
     * @return The updated post
     */
    Post update(UUID id, String title, String content, UUID categoryId);

    /**
     * Deletes a post with the specified ID.
     *
     * @param id The ID of the post to delete
     * @return true if the post was deleted successfully, false otherwise
     */
    boolean deletePost(UUID id);

}
