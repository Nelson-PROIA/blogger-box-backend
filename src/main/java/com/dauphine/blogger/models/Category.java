package com.dauphine.blogger.models;

import java.util.UUID;

/**
 * Represents a category in a blog.
 * <p>
 * This class encapsulates the properties of a blog category, including its unique identifier and name.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
public class Category {

    /**
     * The unique identifier of the category.
     */
    private final UUID id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * Constructs a new Category object with the specified ID and name.
     *
     * @param id   The unique identifier of the category
     * @param name The name of the category
     */
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the unique identifier of the category.
     *
     * @return The ID of the category
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
