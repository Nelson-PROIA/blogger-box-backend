package com.dauphine.blogger.dto;

/**
 * <p>
 * Represents a request for creating or updating a blog category.
 * This class encapsulates the data required to create or update a blog category, including the category name.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class CategoryRequest {

    /**
     * The name of the category.
     */
    private String name;

    /**
     * Default constructor.
     */
    public CategoryRequest() {
    }

    /**
     * Constructs a new CategoryRequest with the specified category name.
     *
     * @param name The name of the category
     */
    public CategoryRequest(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

}
