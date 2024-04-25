package com.dauphine.blogger.models;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents a blog post.
 * <p>
 * This class encapsulates information about a blog post, including its ID, title, content,
 * creation date, and category.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.eu
 */
public class Post {

    /**
     * The unique identifier of the post.
     */
    private final UUID id;
    /**
     * The timestamp when the post was created.
     */
    private final Timestamp createdDate;
    /**
     * The title of the post.
     */
    private String title;
    /**
     * The content of the post.
     */
    private String content;
    /**
     * The category to which the post belongs.
     */
    private Category category;

    /**
     * Constructs a new Post object with the specified parameters.
     *
     * @param id          The unique identifier of the post
     * @param title       The title of the post
     * @param content     The content of the post
     * @param createdDate The timestamp when the post was created
     * @param category    The category to which the post belongs
     */
    public Post(UUID id, String title, String content, Timestamp createdDate, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    /**
     * Retrieves the ID of the post.
     *
     * @return The ID of the post
     */
    public UUID getId() {
        return id;
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
     * @param title The new title for the post
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
     * @param content The new content for the post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the timestamp when the post was created.
     *
     * @return The timestamp of the post creation date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Retrieves the category to which the post belongs.
     *
     * @return The category of the post
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category to which the post belongs.
     *
     * @param category The new category for the post
     */
    public void setCategory(Category category) {
        this.category = category;
    }

}
