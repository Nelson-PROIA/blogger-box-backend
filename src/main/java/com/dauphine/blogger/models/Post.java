package com.dauphine.blogger.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@Entity
@Table(name = "post")
public class Post {

    /**
     * The unique identifier of the post.
     */
    @Id
    @Column(name = "id")
    private UUID id;

    /**
     * The title of the post.
     */
    @Column(name = "title")
    private String title;

    /**
     * The content of the post.
     */
    @Column(name = "content")
    private String content;

    /**
     * The timestamp when the post was created.
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * The category to which the post belongs.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Default constructor.
     * Initializes id, title, content, createdDate, and category to null.
     */
    public Post() {
        this.id = null;
        this.title = null;
        this.content = null;
        this.createdDate = null;
        this.category = null;
    }

    /**
     * Constructs a Post object with the specified ID, title, content, creation date, and category.
     *
     * @param id          The unique identifier of the post
     * @param title       The title of the post
     * @param content     The content of the post
     * @param createdDate The timestamp when the post was created
     * @param category    The category to which the post belongs
     */
    public Post(UUID id, String title, String content, LocalDateTime createdDate, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    /**
     * Constructs a Post object with a random UUID, the specified title, content, and category.
     * The created date will be set to the current system time.
     *
     * @param title    The title of the post
     * @param content  The content of the post
     * @param category The category to which the post belongs
     */
    public Post(String title, String content, Category category) {
        this(UUID.randomUUID(), title, content, LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()), category);
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
     * Sets the ID of the post.
     *
     * @param id The ID of the post
     */
    public void setId(UUID id) {
        this.id = id;
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
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the timestamp when the post was created.
     *
     * @param createdDate The timestamp of the post creation date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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
