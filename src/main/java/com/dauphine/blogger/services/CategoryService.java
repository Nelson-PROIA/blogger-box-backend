package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;

import java.util.List;
import java.util.UUID;

/**
 * Interface defining operations for managing categories in a blog.
 * <p>
 * This interface defines methods for retrieving, creating, updating, and deleting categories.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
public interface CategoryService {

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories
     */
    List<Category> getCategories();

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return The category with the specified ID, or null if not found
     */
    Category getCategory(UUID id);

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category
     * @return The newly created category
     */
    Category createCategory(String name);

    /**
     * Updates the name of a category with the specified ID.
     *
     * @param id   The ID of the category to update
     * @param name The new name for the category
     * @return The updated category, or null if the category with the specified ID was not found
     */
    Category updateCategoryName(UUID id, String name);

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     */
    boolean deleteCategory(UUID id);

}
