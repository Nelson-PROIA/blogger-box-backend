package com.dauphine.blogger.dto;

import java.util.UUID;

/**
 * Represents a request for creating or updating a blog post.
 * <p>
 * This class encapsulates the data required to create or update a blog post,
 * including the title, content, and category ID.
 * <p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class PostRequest {

    /**
     * The title of the post.
     */
    private String postTitle;

    /**
     * The content of the post.
     */
    private String postContent;

    /**
     * The ID of the category to which the post belongs.
     */
    private UUID postCategoryId;

    /**
     * Retrieves the title of the post.
     *
     * @return The title of the post
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the title of the post.
     *
     * @param postTitle The title of the post
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * Retrieves the content of the post.
     *
     * @return The content of the post
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * Sets the content of the post.
     *
     * @param postContent The content of the post
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * Retrieves the ID of the category to which the post belongs.
     *
     * @return The ID of the category
     */
    public UUID getPostCategoryId() {
        return postCategoryId;
    }

    /**
     * Sets the ID of the category to which the post belongs.
     *
     * @param postCategoryId The ID of the category
     */
    public void setPostCategoryId(UUID postCategoryId) {
        this.postCategoryId = postCategoryId;
    }

}
