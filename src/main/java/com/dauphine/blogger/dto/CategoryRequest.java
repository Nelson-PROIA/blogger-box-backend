package com.dauphine.blogger.dto;

/**
 * Represents a request for creating or updating a blog category.
 * <p>
 * This class encapsulates the data required to create or update a blog category,
 * including the category name.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.eu
 */
public class CategoryRequest {

    /**
     * The name of the category.
     */
    private String categoryName;

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the name of the category.
     *
     * @param categoryName The name of the category
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
