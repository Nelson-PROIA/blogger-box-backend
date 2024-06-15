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
    private String title;

    /**
     * The content of the post.
     */
    private String content;

    /**
     * The ID of the category to which the post belongs.
     */
    private UUID categoryId;

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
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

    /**
     * Retrieves the title of the post.
     *
     * @return The title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     *
     * @param title The title of the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the content of the post.
     *
     * @return The content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     *
     * @param content The content of the post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the ID of the category to which the post belongs.
     *
     * @return The ID of the category
     */
    public UUID getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the ID of the category to which the post belongs.
     *
     * @param categoryId The ID of the category
     */
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

}
