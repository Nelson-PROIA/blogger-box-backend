package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the CategoryService interface.
 * <p>
 * This class provides methods to manage categories, including retrieving, creating, updating,
 * and deleting categories.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
@Service
public class CategoryServiceImplementation implements CategoryService {

    /**
     * The list of categories managed by this service.
     */
    private final List<Category> categories;

    /**
     * Constructs a new CategoryServiceImplementation object and initializes the list of categories.
     */
    public CategoryServiceImplementation() {
        categories = new ArrayList<>();

        categories.add(new Category(UUID.randomUUID(), "Science-Fiction"));
        categories.add(new Category(UUID.randomUUID(), "Sport"));
        categories.add(new Category(UUID.randomUUID(), "Theatre"));
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories
     */
    @Override
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return The category with the specified ID, or null if not found
     */
    @Override
    public Category getCategory(UUID id) {
        return categories.stream()
                .filter(category -> id.equals(category.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category
     * @return The newly created category
     */
    @Override
    public Category createCategory(String name) {
        Category category = new Category(UUID.randomUUID(), name);

        categories.add(category);

        return category;
    }

    /**
     * Updates the name of a category with the specified ID.
     *
     * @param id   The ID of the category to update
     * @param name The new name for the category
     * @return The updated category, or null if the category with the specified ID was not found
     */
    @Override
    public Category updateCategoryName(UUID id, String name) {
        Category category = getCategory(id);

        if (category != null) {
            category.setName(name);
        }

        return category;
    }

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteCategory(UUID id) {
        return categories.removeIf(category -> id.equals(category.getId()));
    }

}
