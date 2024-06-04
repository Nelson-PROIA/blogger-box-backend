package com.dauphine.blogger.dto;

import java.util.UUID;

/**
 * <p>
 * Represents a request for creating or updating a blog post.
 * This class encapsulates the data required to create or update a blog post, including the title, content, and category ID.
 * </p>
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
     * Default constructor.
     */
    public PostRequest() {
    }

    /**
     * Constructs a new PostRequest with the specified title, content, and category ID.
     *
     * @param title      The title of the post
     * @param content    The content of the post
     * @param categoryId The ID of the category to which the post belongs
     */
    public PostRequest(String title, String content, UUID categoryId) {
        this.postTitle = title;
        this.postContent = content;
        this.postCategoryId = categoryId;
    }

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
