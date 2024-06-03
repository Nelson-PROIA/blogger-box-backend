package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Interface defining operations for managing categories in a blog.
 * This interface defines methods for retrieving, creating, updating, and deleting categories.
 * </p>
 *
 * <p>
 * All methods in this interface may throw {@link CategoryNotFoundByIdException} if the specified category ID does not exist.
 * For methods that involve creating or updating categories, they may also throw {@link CategoryAlreadyExistsException} if a category with the same name already exists.
 * </p>
 *
 * <p>
 * This interface is designed to provide a contract for classes implementing category management functionality.
 * The methods defined here offer operations to interact with category entities in the underlying data source.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface CategoryService {

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories
     */
    List<Category> getCategories();

    /**
     * Retrieves categories by name.
     *
     * @param name The name of the categories to retrieve
     * @return A list of categories with the specified name
     */
    List<Category> getCategoriesByName(String name);

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return The category with the specified ID
     * @throws CategoryNotFoundByIdException if the category with the specified ID does not exist
     */
    Category getCategory(UUID id) throws CategoryNotFoundByIdException;

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category
     * @return The newly created category
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    Category createCategory(String name) throws CategoryAlreadyExistsException;

    /**
     * Updates the name of a category with the specified ID.
     *
     * @param id   The ID of the category to update
     * @param name The new name for the category
     * @return The updated category
     * @throws CategoryNotFoundByIdException  if the category with the specified ID does not exist
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    Category updateCategoryName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException;

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     * @throws CategoryNotFoundByIdException if the category with the specified ID does not exist
     */
    boolean deleteCategory(UUID id) throws CategoryNotFoundByIdException;

}
